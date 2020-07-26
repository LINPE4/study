package com.enjoy.service.impl;

import com.enjoy.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
public class TransferServiceImpl implements TransferService{
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String transfer(int money) {
        int resultJames = jdbcTemplate.update("INSERT INTO bank_a(money,user_name)VALUES (?,?)",-money,"james");
        int resultPeter = jdbcTemplate.update("INSERT INTO bank_b(money,user_name)VALUES (?,?)",money,"peter");
        if (money > 20){
            throw new RuntimeException("money too large");
        }
        return resultJames+"-"+resultPeter;
    }

}
