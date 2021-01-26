/**
 * Project: springboot-06-dubbo
 * File created at 2021/1/25 18:51
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.provider;


import com.xkcoding.helloworld.model.User;
import com.xkcoding.helloworld.validate.annotation.Validate;
import com.xkcoding.helloworld.validate.inter.QueryOne;

import java.util.List;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type UserProvider
 * @date 2021/1/25 18:51
 */
public interface UserProvider {

    void addUser(User user);

    void addUserList(List<User> userList);
}
