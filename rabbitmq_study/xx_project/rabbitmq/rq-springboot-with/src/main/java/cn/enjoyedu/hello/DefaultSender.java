package cn.enjoyedu.hello;

import cn.enjoyedu.RmConst;
import cn.enjoyedu.config.RabbitConfig;
import cn.enjoyedu.model.User;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**

 *类说明：
 */
@Component
public class DefaultSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        String sendMsg = msg +"---"+ System.currentTimeMillis();;
        System.out.println("Sender : " + sendMsg);
        User user = new User();
        user.setName("user");
        //TODO 普通消息处理
//        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_HELLO, sendMsg);
        //TODO 消息处理--(消费者处理时，有手动应答)
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_HELLO, user);
    }

    public void sendUser(String msg) {
        User user = new User();
        user.setName("user");
        //TODO 消息处理--(消费者处理时，有手动应答)
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_USER, user);
    }

    public void sendDelay() {
        // 通过广播模式发布延时消息 延时30分钟 持久化消息 消费后销毁 这里无需指定路由，会广播至每个绑定此交换机的队列
        rabbitTemplate.convertAndSend(RabbitConfig.Delay_Exchange_Name, "", "orderNo20191228", message ->{
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(30*1000);   // 毫秒为单位，指定此消息的延时时长
            return message;
        });
        System.out.println("sended...");
    }

}
