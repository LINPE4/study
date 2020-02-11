package cn.enjoyedu.controller;

import cn.enjoyedu.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author King老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：
 */
@RestController
@RequestMapping("/rocket")
public class RocketController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MQProducer mqProducer;

    @RequestMapping(value = "/send")
    public String sendrocket(@RequestParam(required = false) String data,
                            @RequestParam(required = false) String tag) {
        try {
            logger.info("rocket的消息={}", data);
            mqProducer.sendMessage(data,"TopicTest", tag, null);
            return "发送rocket成功";
        } catch (Exception e) {
            logger.error("发送rocket异常：", e);
            return "发送rocket失败";
        }
    }

}
