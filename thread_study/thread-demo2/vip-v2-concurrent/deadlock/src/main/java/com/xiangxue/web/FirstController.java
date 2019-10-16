package com.xiangxue.web;

import com.xiangxue.normal.ServiceA;
import com.xiangxue.servlet3.ServiceB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *
 *往期视频咨询芊芊老师  QQ：2130753077  VIP课程咨询 依娜老师QQ：2133576719
 *
 *类说明：
 */
@Controller
@RequestMapping(produces="text/html;charset=UTF-8")
public class FirstController {

    private static Logger logger
            = LoggerFactory.getLogger(FirstController.class);

    @Autowired
    private ServiceA serviceA;
    @Autowired
    private ServiceB serviceB;

    @RequestMapping("/first")
    @ResponseBody
    @PostConstruct
    public String hello(){
        synchronized (serviceA){
            logger.info("Get serviceA");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (serviceB){
                logger.info("Get serviceB");
                return "第一个服务";
            }
        }
    }

}
