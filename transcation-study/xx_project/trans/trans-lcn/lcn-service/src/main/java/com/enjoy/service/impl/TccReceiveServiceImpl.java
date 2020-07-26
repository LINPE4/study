package com.enjoy.service.impl;

import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.enjoy.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.codingapi.txlcn.tracing.TracingContext.tracing;

@com.alibaba.dubbo.config.annotation.Service
public class TccReceiveServiceImpl implements ReceiveService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //try   不再是数据操作
    //lcn子事务   当前事务上下文中有了groupId，则加入，没有则新建一个
    @TccTransaction(confirmMethod = "confirm", cancelMethod = "cancel", executeClass = TccReceiveServiceImpl.class)
    public void receiveMoney(int money) {
        String groupId = TracingContext.tracing().groupId();
        int resultUser = jdbcTemplate.update("INSERT INTO bank_b(money,user_name,status)VALUES (?,?,?)",money,"peter",groupId);
    }

    //确认一下
    public void confirm(int money) {
        String groupId = TracingContext.tracing().groupId();
        int resultUser = jdbcTemplate.update("UPDATE bank_b SET status = 1 WHERE status = ?", tracing().groupId());
    }

    //取消一下
    public void cancel(int money) {
        String groupId = TracingContext.tracing().groupId();
        int resultUser = jdbcTemplate.update("INSERT INTO bank_b(money,user_name,status)VALUES (?,?,?)",-money,"peter",groupId);
    }
}
