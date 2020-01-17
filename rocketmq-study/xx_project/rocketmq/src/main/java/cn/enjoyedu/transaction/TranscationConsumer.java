package cn.enjoyedu.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 【享学课堂】 King老师
 * 消费者-事务消息的消费者
 */

public class TranscationConsumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_producer");
        consumer.setNamesrvAddr("192.168.0.128:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTransaction", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            private Random random = new Random();

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    System.out.println("获取到消息开始消费："+msg + " , content : " + new String(msg.getBody()));
                }
                try {
                    // 模拟业务处理
                    TimeUnit.SECONDS.sleep(random.nextInt(5));
                } catch (Exception e) {
                    e.printStackTrace();
                    //返回处理失败，该消息后续可以继续被消费
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                //返回处理成功，该消息就不会再次投递过来了
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer start ! ");
    }
}
