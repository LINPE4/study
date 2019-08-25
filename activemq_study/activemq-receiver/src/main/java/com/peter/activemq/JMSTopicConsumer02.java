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
public class JMSTopicConsumer02 {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory
                        ("tcp://192.168.140.128:61616");
        Connection connection=null;
        try {

            connection=connectionFactory.createConnection();
            connection.start();

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