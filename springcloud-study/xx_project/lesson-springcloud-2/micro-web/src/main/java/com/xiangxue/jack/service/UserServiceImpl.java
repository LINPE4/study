package com.xiangxue.jack.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {

    private AtomicInteger s = new AtomicInteger();
    private AtomicInteger f = new AtomicInteger();

    public static String SERVIER_NAME = "micro-order";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String queryContents() {
        s.incrementAndGet();
        String results = restTemplate.getForObject("http://"
                + SERVIER_NAME + "/queryUser", String.class);
        return results;
    }
}
