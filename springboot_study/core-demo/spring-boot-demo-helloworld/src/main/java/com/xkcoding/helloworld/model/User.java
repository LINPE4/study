/**
 * Project: springboot-06-dubbo
 * File created at 2021/1/25 18:52
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.model;

import com.xkcoding.helloworld.validate.annotation.Mobile;
import com.xkcoding.helloworld.validate.inter.QueryOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type User
 * @date 2021/1/25 18:52
 */
public class User {

    private Integer id;

    @NotNull(message = "用户名称不能为空", groups = QueryOne.class)
    private String userName;

    @Mobile(groups = QueryOne.class)
    private String mobile;

    @NotNull(message = "地址对象不能为空", groups = QueryOne.class)
    @Valid
    private Address address;

//    @NotNull(message = "地址列表不能为空", groups = QueryOne.class)
//    @Valid
    private List<Address> addressList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address=" + address +
                '}';
    }
}
