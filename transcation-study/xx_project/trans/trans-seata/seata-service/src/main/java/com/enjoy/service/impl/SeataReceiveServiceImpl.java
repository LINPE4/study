package com.enjoy.service.impl;

import com.enjoy.service.ReceiveService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@com.alibaba.dubbo.config.annotation.Service
public class SeataReceiveServiceImpl implements ReceiveService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GlobalTransactional
    public void receiveMoney(int money) {
        int resultUser = jdbcTemplate.update("INSERT INTO bank_b(money,user_name)VALUES (?,?)",money,"peter");
        System.out.println("收帐结束");
    }
}
