package com.peter.shiro.controller;

import com.peter.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pub")
public class PublicController {

    @Autowired
    private UserService userService;


    @RequestMapping("find_user_info")
    public Object findUserInfo(@RequestParam("username")String username){

        return userService.findAllUserInfoByUsername(username);
    }

}
