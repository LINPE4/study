package cn.enjoyedu.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 * @author 【享学课堂】 King老师
 * 消费者-Filter过滤 (这个新版本淘汰了)
 */
public class FilterServerConsumer {

    public static void main(String[] args) throws InterruptedException, MQClientException, IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("FilterServer");
        consumer.setNamesrvAddr("192.168.0.128:9876");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File classFile = new File(classLoader.getResource("MessageFilterImpl.java").getFile());


        String filterCode = MixAll.file2String(classFile);
        System.out.println(filterCode);
        consumer.subscribe("TopicTest", "org.apache.rocketmq.example.filter.MessageFilterImpl",
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
