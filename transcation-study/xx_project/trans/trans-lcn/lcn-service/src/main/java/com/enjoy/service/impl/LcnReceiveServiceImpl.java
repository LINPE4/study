package com.enjoy.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.enjoy.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//@com.alibaba.dubbo.config.annotation.Service
public class LcnReceiveServiceImpl implements ReceiveService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //3pc事务
    @LcnTransaction
    public void receiveMoney(int money) {
        int resultUser = jdbcTemplate.update("INSERT INTO bank_b(money,user_name)VALUES (?,?)",money,"peter");
        System.out.println(TracingContext.tracing().groupId());
    }
}
