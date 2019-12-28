/**
 * Project: rabbitmq
 * File created at 2019/12/28 14:39
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.delay;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type TimeoutDLX
 * @date 2019/12/28 14:39
 */
public class TimeoutDLX {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionFactory.getConnection();
        Channel channel = connection.createChannel();
        //创建DLX及死信队列
        channel.exchangeDeclare("dlx.exchange", "direct");
        channel.queueDeclare("dlx.queue", true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "dlx.routingKey");
        //创建测试超时的Exchange及Queue
        channel.exchangeDeclare("delay.exchange", "direct");
        Map<String, Object> arguments = new HashMap<String, Object>();
        //过期时间10s
        arguments.put("x-message-ttl", 10000);
        //绑定DLX
        arguments.put("x-dead-letter-exchange", "dlx.exchange");
        //绑定发送到DLX的RoutingKey
        arguments.put("x-dead-letter-routing-key", "dlx.routingKey");
        channel.queueDeclare("delay.queue", true, false, false, arguments);
        channel.queueBind("delay.queue", "delay.exchange", "delay.routingKey");
        //发布一条消息
        channel.basicPublish("delay.exchange", "delay.routingKey", null, "该消息将在10s后发送到延迟队列".getBytes());
        channel.close();
        connection.close();
    }
}
