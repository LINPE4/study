package com.xiangxue.jack.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @RequestMapping("/queryUser")
    public String queryUser() {
        log.info("========micro-order===queryUser");
        return "========micro-order===queryUser";
    }
}
