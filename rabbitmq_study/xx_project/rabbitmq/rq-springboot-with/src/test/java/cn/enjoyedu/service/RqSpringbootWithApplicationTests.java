package cn.enjoyedu.service;

import cn.enjoyedu.config.RabbitConfig;
import cn.enjoyedu.test.Parment;
import cn.enjoyedu.test.model.CModel1;
import cn.enjoyedu.test.model.ParmentModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RqSpringbootWithApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private List<Parment> parments;

    @Test
    public void delayMsgTest() {
        // 通过广播模式发布延时消息 延时30分钟 持久化消息 消费后销毁 这里无需指定路由，会广播至每个绑定此交换机的队列
        rabbitTemplate.convertAndSend(RabbitConfig.Delay_Exchange_Name, "", "orderNo20191228", message ->{
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(10*1000);   // 毫秒为单位，指定此消息的延时时长
            return message;
        });
        System.out.println("sended...");
    }

    @Test
    public void test() {
        ParmentModel pm = new ParmentModel();
        pm.setName("c1");
        CModel1 cm1 = new CModel1();
        cm1.setName("c1 name");
        cm1.setName1("c1 name1");
        pm.setData(cm1);
        for (Parment p: parments) {
            if (p.adapter(pm.getName())) {
                p.test(pm);
            }
        }
    }

}
