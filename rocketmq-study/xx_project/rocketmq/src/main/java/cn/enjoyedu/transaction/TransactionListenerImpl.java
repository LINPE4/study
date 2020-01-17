package cn.enjoyedu.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author 【享学课堂】 King老师
 * 事务监听机制，主要是执行事务以及事务回查
 */
public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);
    //使用transactionId
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    //TODO 执行事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //TODO 执行本地事务开始
        int value = transactionIndex.getAndIncrement();
        //业务处理
        System.out.println("执行本地事务..."+value);
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        switch (status) {
            case 0:
                //LocalTransactionState.UNKNOW表示未知的事件，需要RocketMQ进一步服务业务进行确认该交易的处理
                return LocalTransactionState.UNKNOW;
            case 1:
                return LocalTransactionState.COMMIT_MESSAGE;
            case 2:
                return LocalTransactionState.ROLLBACK_MESSAGE; //这条消息抛弃了（账户余额不足 1W）
            default:
                return LocalTransactionState.COMMIT_MESSAGE;
        }
        //TODO 执行本地事务结束
    }
    //该方法用于RocketMQ与业务确认未提交事务的消息的状态（一分钟执行一次）
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("事务回查-----UNKNOW-----------");
        Integer status = localTrans.get(msg.getTransactionId());
        //业务处理（1分钟）
        int mod = msg.getTransactionId().hashCode() % 2;
        if (null != status) {
            switch (mod) {
                case 0:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                default:
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
