package cn.enjoyedu;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author King老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 */
@Component
public class MQPushConsumer implements MessageListenerConcurrently {
    private  static final Logger LOGGER = LoggerFactory.getLogger(MQPushConsumer.class);

    @Value("${rocketmq.namesrvaddr}")
    private String nameservAddr;


    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestConsumer");

    /*
     * 初始化
     */
    @PostConstruct
    public void  start(){
        try {
            LOGGER.info("MQ:启动消费者");
            consumer.setNamesrvAddr(nameservAddr);
            //消息队列从头开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //集群消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.subscribe("TopicTest","*");
            //注册消息监听器
            consumer.registerMessageListener(this);
            consumer.start();
        }catch (MQClientException e){
            LOGGER.error("MQ:启动消费者失败:{}-{}",e.getResponseCode(),e.getErrorMessage());
            throw  new RuntimeException(e.getErrorMessage(),e);
        }
    }

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        int index =0;
        try {
            for(;index<msgs.size();index++){
                MessageExt msg = msgs.get(index);
                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                System.out.printf("消费者监听： queueID:%d:Messages:%s %n",  msgs.get(index).getQueueId(),messageBody);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }finally {
            if(index <msgs.size()){
                context.setAckIndex(index+1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
    @PreDestroy
    public void stop(){
        if(consumer !=null){
            consumer.shutdown();
            LOGGER.error("MQ:关闭消费者");
        }
    }
}
