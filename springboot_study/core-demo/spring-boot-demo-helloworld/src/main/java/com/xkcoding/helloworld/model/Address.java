/**
 * Project: springboot-06-dubbo
 * File created at 2021/1/25 18:53
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.model;

import com.xkcoding.helloworld.validate.inter.QueryOne;

import javax.validation.constraints.NotNull;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type Address
 * @date 2021/1/25 18:53
 */
public class Address {

    private Integer id;

    @NotNull(message = "地址不能为空", groups = QueryOne.class)
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
