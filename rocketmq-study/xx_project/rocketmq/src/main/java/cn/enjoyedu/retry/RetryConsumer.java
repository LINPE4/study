package cn.enjoyedu.retry;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueByConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author 【享学课堂】 King老师
 * 消费者-推模式
 */

public class RetryConsumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.subscribe("TopicTest", "*");
        consumer.setNamesrvAddr("192.168.0.128:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//每次从最后一次消费的地址
        consumer.setMaxReconsumeTimes(3);//重试次数（默认15）
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("queueID:%d:%s:Messages:%s %n",  msgs.get(0).getQueueId(),Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                // try 有异常  失败了 RECONSUME_LATER （消费-> 扣款，->余额不足，）
                //这里返回RECONSUME_LATER，这个消息会重新发送到Broker中的RETRY topic
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
        System.out.printf("ConsumerPartOrder Started.%n");
    }
}
