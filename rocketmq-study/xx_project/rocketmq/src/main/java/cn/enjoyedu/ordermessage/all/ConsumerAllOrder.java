package cn.enjoyedu.ordermessage.all;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author 【享学课堂】 King老师
 * 消费者-推模式
 */

public class ConsumerAllOrder {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("AllOrderGroup");
        consumer.subscribe("AllOrder", "*");
        consumer.setNamesrvAddr("192.168.0.128:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//每次从最后一次消费的地址

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                System.out.printf("%s:Messages:%s %n", Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("ConsumerPartOrder Started.%n");
    }
}
