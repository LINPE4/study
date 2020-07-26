package com.enjoy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.enjoy.service.ReceiveService;
import com.enjoy.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//@org.springframework.stereotype.Service
public class DubboTransferService implements TransferService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Reference
    private ReceiveService receiveService;

    @LcnTransaction
    public String transfer(int money) {
        int resultUser = jdbcTemplate.update("INSERT INTO bank_a(money,user_name)VALUES (?,?)",-money,"james");

        System.out.println(TracingContext.tracing().groupId());
        receiveService.receiveMoney(money);//一样隐藏地传递了groupid
        if (money > 20){
            throw new RuntimeException("money too large");
        }
        return resultUser+"-";
    }

}
