package cn.enjoyedu.filter;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
/**
 * @author 【享学课堂】 King老师
 * 生产者-Filter过滤(这个新版本淘汰了)
 */
public class FilterServerProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.0.128:9876");
        producer.start();

        try {
            for (int i = 0; i < 100; i++) {
                Message msg = new Message("TopicFilter",
                    "TagA",
                    "OrderID001",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));

                msg.putUserProperty("SequenceId", String.valueOf(i));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
