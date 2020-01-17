package cn.enjoyedu.ordermessage.part;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
/**
 * @author 【享学课堂】 King老师
 * 生产者——顺序消息(只支持同步发送)
 */
public class ProducerPartOrder {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            MQProducer producer = new DefaultMQProducer("orderGroup");
            ((DefaultMQProducer) producer).setNamesrvAddr("192.168.0.128:9876");
            producer.start();
            //对于一个指定的Topic，所有消息根据tag来进行分区划分，同一个分区内的消息按照严格的FIFO顺序进行发布和消费
            String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD"};
            for (int i = 0; i < 12; i++) {
                int index =i % tags.length;
                int orderId = i ;
                Message msg = new Message("PartOrder", tags[index], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //使用selector选择器控制消息往哪个queue发
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        return mqs.get(index);
                    }
                }, orderId);

                System.out.printf("queueID%s %n", sendResult.getMessageQueue().getQueueId()+":"+msg.getTags()+":"+new String(msg.getBody()));
            }
            producer.shutdown();
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
