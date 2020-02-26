package cn.enjoyedu.controller;


import cn.enjoyedu.config.RabbitConfig;
import cn.enjoyedu.fanout.FanoutSender;
import cn.enjoyedu.hello.DefaultSender;
import cn.enjoyedu.topic.TopicSender;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**

 *类说明： localhost:8080/rabbit/hello
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private DefaultSender defaultSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;


    /**
     * 普通类型测试
     */
    @GetMapping("/hello")
    public void hello() { //mq的消息发送
        defaultSender.send("hellomsg!");
    }

    /**
     * 普通类型测试
     */
    @GetMapping("/user")
    public void user() { //mq的消息发送
        defaultSender.sendUser("hellomsg!");
    }

    /**
     * topic exchange类型rabbitmq测试
     */
    @GetMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     * fanout exchange类型rabbitmq测试
     */
    @GetMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.send("hellomsg:OK");
    }

    @GetMapping("/delayMsgTest")
    public void delayMsgTest() {
        defaultSender.sendDelay();
    }
}
