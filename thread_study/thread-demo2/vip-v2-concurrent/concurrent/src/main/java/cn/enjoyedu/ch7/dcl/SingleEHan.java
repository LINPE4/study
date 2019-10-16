package cn.enjoyedu.ch7.dcl;

/**
 * 饿汉式
 * 枚举
 */
public class SingleEHan {
    private SingleEHan(){}
    private static SingleEHan singleDcl = new SingleEHan();

}
