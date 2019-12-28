/**
 * Project: rabbitmq
 * File created at 2019/12/28 14:43
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.delay;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type DelayExchangeDemo
 * @date 2019/12/28 14:43
 */
public class DelayExchangeDemo {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionFactory.getConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-delayed-type", "direct");
        channel.exchangeDeclare("delay.plugin.exchange",  BuiltinExchangeType.DIRECT, true, false, arguments);
        channel.queueDeclare("delay.plugin.queue", true, false, false, null);
        channel.queueBind("delay.plugin.queue", "delay.plugin.exchange", "delay.plugin.routingKey");
        Map<String, Object> headers = new HashMap<String, Object>();
        //延迟10s后发送
        headers.put("x-delay", 10000);
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.headers(headers);
        channel.basicPublish("delay.plugin.exchange", "delay.plugin.routingKey", builder.build(), "该消息将在10s后发送到队列".getBytes());
        channel.close();
        connection.close();
    }
}
