/**
 * Project: rabbitmq
 * File created at 2019/12/28 15:53
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.listener;

import cn.enjoyedu.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import java.io.IOException;


/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type PayTimeOutConsumer
 * @date 2019/12/28 15:53
 */
@Component
public class PayTimeOutConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = RabbitConfig.Timeout_Trade_Queue_Name)
    @RabbitHandler
    public void process(String tradeCode, Message message, Channel channel) throws IOException {
        try {
            logger.info("开始执行订单[{}]的支付超时订单关闭......", tradeCode);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("超时订单处理完毕");
        } catch (Exception e) {
            logger.error("超时订单处理失败:{}", tradeCode);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
