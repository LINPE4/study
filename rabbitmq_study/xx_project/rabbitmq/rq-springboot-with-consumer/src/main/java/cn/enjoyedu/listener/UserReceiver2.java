package cn.enjoyedu.listener;

import cn.enjoyedu.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**

 *类说明：
 */
@Component
@RabbitListener(queues = "sb.hello")
public class UserReceiver2 {
    @RabbitHandler
    public void process(User user) {
        System.out.println("user Receiver 22 : " + user);
    }


}
