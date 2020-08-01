package com.xiangxue.jack.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RefreshScope
@Slf4j
@RestController
public class UserController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/queryUser")
    public String queryUser() {
        log.info("========micro-order===queryUser");
        return "========micro-order===queryUser";
    }

    @Value("${username}")
    private String username;

    @Value("${redis.password}")
    private String redispass;

    @Value("${db.password}")
    private String dbpass;

    @Autowired
    Environment environment;

    @RequestMapping("/queryContent")
    public String queryContent(HttpServletRequest request) {
        logger.info(""+this.hashCode());
        logger.info("==================已经调用==========" + request.getRemotePort());
        logger.info("@Value======username======" + username);
        logger.info("Environment======username======" + environment.getProperty("username"));
        logger.info("@Value======redispass======" + redispass);
        logger.info("Environment======redispass======" + environment.getProperty("redis.password"));


        logger.info("zookeeper======zk.jack.url======" + environment.getProperty("configzk.jack.url8"));
        return "hello xxxxx";
    }

}
