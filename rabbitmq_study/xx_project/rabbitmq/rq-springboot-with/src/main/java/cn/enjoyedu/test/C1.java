/**
 * Project: ams-abd-ast
 * File created at 2020/2/11 17:12
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.test;

import cn.enjoyedu.test.model.CModel1;
import cn.enjoyedu.test.model.ParmentModel;
import org.springframework.stereotype.Service;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type C1
 * @date 2020/2/11 17:12
 */
@Service
public class C1 implements Parment {
    @Override
    public Boolean adapter(String name) {
        return name.equals("c1");
    }

    @Override
    public void test(ParmentModel parmentModel) {
        System.out.println("c1");
        CModel1 c1Model = (CModel1) parmentModel.getData();
        System.out.println(c1Model.getName());
        System.out.println(c1Model.getName1());
    }
}
