package cn.enjoyedu.config;

import cn.enjoyedu.RmConst;
import cn.enjoyedu.hello.UserReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**

 *类说明：
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Autowired
    private UserReceiver userReceiver;

    //TODO 连接工厂
    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        //TODO 消息发送确认
        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }
    //TODO rabbitAdmin类封装对RabbitMQ的管理操作
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    //TODO 使用Template
    @Bean
    public RabbitTemplate newRabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        //TODO 失败通知
        template.setMandatory(true);
        //TODO 发送方确认
        template.setConfirmCallback(confirmCallback());
        //TODO 失败回调
        template.setReturnCallback(returnCallback());
        //TODO 设置其消息转换器
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    //===============使用了RabbitMQ系统缺省的交换器（direct交换器）==========
    //TODO 申明队列（最简单的方式）
    @Bean
    public Queue helloQueue() {
        return new Queue(RmConst.QUEUE_HELLO);
    }
    @Bean
    public Queue userQueue() { return new Queue(RmConst.QUEUE_USER); }



    //===============以下是验证topic Exchange==========
    @Bean
    public Queue queueEmailMessage() {
        return new Queue(RmConst.QUEUE_TOPIC_EMAIL);
    }

    @Bean
    public Queue queueUserMessages() {
        return new Queue(RmConst.QUEUE_TOPIC_USER);
    }
    //TODO 申明交换器(topic交换器)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RmConst.EXCHANGE_TOPIC);
    }
    //TODO 绑定关系
    @Bean
    public Binding bindingEmailExchangeMessage() {
        return BindingBuilder
                .bind(queueEmailMessage())
                .to(exchange())
                .with("sb.*.email");
    }
    @Bean
    public Binding bindingUserExchangeMessages() {
        return BindingBuilder
                .bind(queueUserMessages())
                .to(exchange())
                .with("sb.*.user");
    }

    //===============以上是验证topic Exchange==========

    //===============以下是验证Fanout Exchange==========
    //TODO 申明队列
    @Bean
    public Queue AMessage() {
        return new Queue("sb.fanout.A");
    }
    //TODO 申明交换器(fanout交换器)
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RmConst.EXCHANGE_FANOUT);
    }
    //TODO 绑定关系
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    //===============以上是验证Fanout Exchange的交换器==========

    //===============消费者确认==========
//    @Bean
//    public SimpleMessageListenerContainer messageContainer() {
//        SimpleMessageListenerContainer container
//                = new SimpleMessageListenerContainer(connectionFactory());
//        //TODO 绑定了这个sb.user队列
//        container.setQueues(userQueue());
//        //TODO 手动提交
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        //TODO 消费确认方法
//        container.setMessageListener(userReceiver);
//        return container;
//    }

    //===============生产者发送确认==========
    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback(){
        return new RabbitTemplate.ConfirmCallback(){

            @Override
            public void confirm(CorrelationData correlationData,
                                boolean ack, String cause) {
                if (ack) {
                    System.out.println("发送者确认发送给mq成功");
                } else {
                    //处理失败的消息
                    System.out.println("发送者发送给mq失败,考虑重发:"+cause);
                }
            }
        };
    }
    //===============失败通知==========
    @Bean
    public RabbitTemplate.ReturnCallback returnCallback(){
        return new RabbitTemplate.ReturnCallback(){

            @Override
            public void returnedMessage(Message message,
                                        int replyCode,
                                        String replyText,
                                        String exchange,
                                        String routingKey) {
                System.out.println("无法路由的消息，需要考虑另外处理。");
                System.out.println("Returned replyText："+replyText);
                System.out.println("Returned exchange："+exchange);
                System.out.println("Returned routingKey："+routingKey);
                String msgJson  = new String(message.getBody());
                System.out.println("Returned Message："+msgJson);
            }
        };
    }

    //===============延迟队列==========
    // 支付超时延时交换机
    public static final String Delay_Exchange_Name = "delay.exchange";

    // 超时订单关闭队列
    public static final String Timeout_Trade_Queue_Name = "close_trade";

    @Bean
    public Queue delayPayQueue() {
        return new Queue(RabbitConfig.Timeout_Trade_Queue_Name, true);
    }
    // 定义广播模式的延时交换机 无需绑定路由
    @Bean
    FanoutExchange delayExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(RabbitConfig.Delay_Exchange_Name, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }
    // 绑定延时队列与交换机
    @Bean
    public Binding delayPayBind() {
        return BindingBuilder.bind(delayPayQueue()).to(delayExchange());
    }
    //===============延迟队列end==========

    // 定义消息转换器
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
