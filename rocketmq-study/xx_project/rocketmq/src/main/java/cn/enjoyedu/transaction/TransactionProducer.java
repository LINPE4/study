package cn.enjoyedu.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;
/**
 * @author 【享学课堂】 King老师
 * 消费者-事务消息的生产者
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListener transactionListener = new TransactionListenerImpl();
        //支持事务的生产者 TransactionMQProducer
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer");
        producer.setNamesrvAddr("192.168.0.128:9876");
        //设置用于事务消息的处理线程池
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        producer.setExecutorService(executorService);
        //设置事务监听器，监听器实现接口org.apache.rocketmq.client.producer.TransactionListener
        //监听器中实现需要处理的交易业务逻辑的处理，以及MQ Broker中未确认的事务与业务的确认逻辑
        producer.setTransactionListener(transactionListener);
        producer.start();

        //生成不同的Tag，用于模拟不同的处理场景
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {//10条消息
            try {
                //组装产生消息
                Message msg =
                        new Message("TopicTransaction", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //以事务发送消息，并在事务消息被成功预写入到RocketMQ中后，执行用户定义的交易逻辑，
                //交易逻辑执行成功后，再实现实现业务消息的提交逻辑
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);
                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
