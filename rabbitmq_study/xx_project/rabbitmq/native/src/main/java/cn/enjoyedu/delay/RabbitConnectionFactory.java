/**
 * Project: rabbitmq
 * File created at 2019/12/28 14:38
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.delay;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type RabbitConnectionFactory
 * @date 2019/12/28 14:38
 */
public class RabbitConnectionFactory {
    private static final String IP_ADDRESS = "120.79.185.72";
    private static final int PORT = 5672;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "151310";

    private static ConnectionFactory factory = new ConnectionFactory();

    static {
        factory.setHost(IP_ADDRESS);
//        factory.setPort(PORT);
//        factory.setUsername(USERNAME);
//        factory.setPassword(PASSWORD);
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
