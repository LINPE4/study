package com.peter.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 *
 * 此模式无法接收离线前的消息
 */
public class JMSTopicConsumer01 {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory
                        ("tcp://192.168.140.128:61616");
        Connection connection=null;
        try {

            connection=connectionFactory.createConnection();
            connection.start();

            /**
             * Boolean.TRUE： 开启事务， 需要session.commit();才能消费消息
             * Boolean.FALSE： 不开启事务
             *      Session.AUTO_ACKNOWLEDGE  直接消费消息
             *      CLIENT_ACKNOWLEDGE        需要session.commit();消费消息
             *      DUPS_OK_ACKNOWLEDGE       延迟消费，存在重复消费的可能
             */
            Session session=connection.createSession
                    (Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //创建目的地
            Destination destination=session.createTopic("myTopic");
            //创建发送者
            MessageConsumer consumer=session.createConsumer(destination);

            TextMessage textMessage=(TextMessage) consumer.receive();
            System.out.println(textMessage.getText());

            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
