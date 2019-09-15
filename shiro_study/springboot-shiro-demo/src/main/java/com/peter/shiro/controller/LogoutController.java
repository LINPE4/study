package com.peter.shiro.controller;


import com.peter.shiro.domain.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LogoutController {


//    @RequestMapping("/logout")
//    public JsonData findMyPlayRecord(){
//
//        Subject subject = SecurityUtils.getSubject();
//
//        if(subject.getPrincipals() != null ){
//
//        }
//
//        SecurityUtils.getSubject().logout();
//
//        return JsonData.buildSuccess("logout成功");
//
//    }

}
