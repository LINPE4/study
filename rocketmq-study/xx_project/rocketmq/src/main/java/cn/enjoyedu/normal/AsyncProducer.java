package cn.enjoyedu.normal;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 【享学课堂】 King老师
 * 异步发送
 */
public class AsyncProducer {
    public static void main(
            String[] args) throws MQClientException, InterruptedException, UnsupportedEncodingException {
        //生产者实例化
        DefaultMQProducer producer = new DefaultMQProducer("async");
        //指定rocket服务器地址
        producer.setNamesrvAddr("192.168.0.128:9876");

        //启动实例
        producer.start();
        //发送异步失败时的重试次数(默认值)
        producer.setRetryTimesWhenSendAsyncFailed(2);

        int messageCount = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                final int index = i;
                Message msg = new Message("TopicTest",
                        "TagC",
                        "OrderID"+index,
                        ("Hello world "+index).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //生产者异步发送
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %s %n", index, new String(msg.getBody()));
                    }
                    @Override
                    public void onException(Throwable e) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Thread.sleep(1000);
        countDownLatch.await(100, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
