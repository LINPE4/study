package cn.enjoyedu.ordermessage.all;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author 【享学课堂】 King老师
 * 同步发送--顺序发送
 */
public class ProducerAllOrder {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("AllOrder");
        producer.setNamesrvAddr("192.168.0.128:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("AllOrder" ,
                    "TagA" ,"KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n%n%n", sendResult.getSendStatus()+":(MsgId):"
                    +sendResult.getMsgId()+":(queueId):"
                    +sendResult.getMessageQueue().getQueueId()
                    +"(value):"+ new String(msg.getBody()));
        }
        producer.shutdown();
    }
}

