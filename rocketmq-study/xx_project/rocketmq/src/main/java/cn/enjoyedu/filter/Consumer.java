package cn.enjoyedu.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Consumer {

    public static void main(String[] args) throws InterruptedException, MQClientException, IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupNamecc4");
        consumer.setNamesrvAddr("127.0.0.1:9876");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File classFile = new File(classLoader.getResource("MessageFilterImpl.java").getFile());

        String filterCode = MixAll.file2String(classFile);
        consumer.subscribe("TopicFilter", "cn.enjoyedu.filter.MessageFilterImpl",
            filterCode);

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.out.printf("ConsumerPartOrder Started.%n");
    }
}
