/**
 * Project: springboot-06-dubbo
 * File created at 2021/1/25 18:51
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.provider.impl;

import com.xkcoding.helloworld.model.User;
import com.xkcoding.helloworld.provider.UserProvider;
import com.xkcoding.helloworld.validate.annotation.Validate;
import com.xkcoding.helloworld.validate.inter.QueryOne;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type UserProviderImpl
 * @date 2021/1/25 18:51
 */
@Service
public class UserProviderImpl implements UserProvider {


    @Override
    public void addUser(@Validate({QueryOne.class}) User user) {
        System.out.println(user);
    }

    @Override
    public void addUserList(@Validate({QueryOne.class}) List<User> userList) {
        System.out.println(userList);
    }
}
