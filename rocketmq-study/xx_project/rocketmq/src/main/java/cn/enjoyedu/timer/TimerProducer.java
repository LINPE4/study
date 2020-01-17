package cn.enjoyedu.timer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author 【享学课堂】 King老师
 * 延时发送
 */
public class TimerProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("sync");
        producer.setNamesrvAddr("192.168.0.128:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest" ,
                    "TagB" ,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //这个是设置延时消息的属性
            //"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"  18个等级
            msg.setDelayTimeLevel(4);
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}

