/**
 * Project: ams-abd-ast
 * File created at 2020/2/11 17:12
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package cn.enjoyedu.test;

import cn.enjoyedu.test.model.CModel2;
import cn.enjoyedu.test.model.ParmentModel;
import org.springframework.stereotype.Service;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type C2
 * @date 2020/2/11 17:12
 */
@Service
public class C2 implements Parment {

    @Override
    public Boolean adapter(String name) {
        return name.equals("c2");
    }

    @Override
    public void test(ParmentModel parmentModel) {
        System.out.println("c2");
        System.out.println(parmentModel);
        CModel2 c1Mode2 = (CModel2) parmentModel.getData();
        System.out.println(c1Mode2);
    }
}
