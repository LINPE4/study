package com.enjoy.service.impl;

import com.enjoy.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

//@org.springframework.stereotype.Service
public class RestTransferService implements TransferService {
    private RestTemplate restTemplate =new RestTemplate();

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @LcnTransaction   //lcn子事务 groupid传递过去
    public String transfer(int money) {
//        String appList = tracing().appMapString();
//        String groupId = tracing().groupId();
//        String lcn_param = "&appList="+appList+"&groupId="+groupId;

        int resultUser = jdbcTemplate.update("INSERT INTO bank_a(money,user_name)VALUES (?,?)",-money,"james");
        String url = "http://localhost:5003/receive?money={money}";
        String resultGood = restTemplate.postForObject(url, "money", String.class, money);
        if (money > 20){
            throw new RuntimeException("money too large");
        }
        return resultUser+"-";
    }
}
