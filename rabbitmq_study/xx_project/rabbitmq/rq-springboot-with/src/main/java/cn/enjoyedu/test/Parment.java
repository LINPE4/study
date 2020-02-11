/**
 * Project: ams-abd-ast
 * File created at 2020/2/11 17:10
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.test;


import cn.enjoyedu.test.model.ParmentModel;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type parment
 * @date 2020/2/11 17:10
 */
public interface Parment {
    Boolean adapter(String name);

    void test(ParmentModel parmentModel);
}
