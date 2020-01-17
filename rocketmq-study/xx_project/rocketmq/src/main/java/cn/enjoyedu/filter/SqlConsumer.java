package cn.enjoyedu.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class SqlConsumer {

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_4");
        consumer.setNamesrvAddr("192.168.0.128:9876");
        try {
            consumer.subscribe("SQL",
                    MessageSelector.bySql(" TAGS is not null and TAGS in ('TagA', 'TagB')  "));
        } catch (MQClientException e) {
            e.printStackTrace();
            return;
        }

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                ConsumeConcurrentlyContext context) {
                System.out.printf("queueID:%d:%s:Messages:%s %n",  msgs.get(0).getQueueId(),msgs.get(0).getTags(), new String(msgs.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            return;
        }
        System.out.printf("ConsumerPartOrder Started.%n");
    }
}
