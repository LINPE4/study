package com.enjoy.controller;

import com.enjoy.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private TransferService transferService;

    @GetMapping("/transfer")
    public String transfer(int money){
        return transferService.transfer(money);
    }

}
