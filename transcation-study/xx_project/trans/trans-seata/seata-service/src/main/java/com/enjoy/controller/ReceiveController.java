package com.enjoy.controller;

import com.enjoy.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReceiveController {

    @Autowired
    private ReceiveService receiveService;

    @PostMapping("/receive")
    public String receive(HttpServletRequest request) {
//        String groupId = request.getParameter("groupId");
//        String appList = request.getParameter("appList");
//        TracingContext.tracing().init(Maps.newHashMap(TracingConstants.GROUP_ID, groupId, TracingConstants.APP_MAP, appList));

        int money = Integer.parseInt(request.getParameter("money"));
        receiveService.receiveMoney(money);
        return "success";
    }
}
