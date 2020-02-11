/**
 * Project: ams-abd-ast
 * File created at 2020/2/11 17:11
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.test.model;


/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type ParmentModel
 * @date 2020/2/11 17:11
 */
public class ParmentModel<T> {

    private String name;

    private T data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
