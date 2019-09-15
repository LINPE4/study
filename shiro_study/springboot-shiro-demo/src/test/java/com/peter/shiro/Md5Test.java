package com.peter.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class Md5Test {


    @Test
    public void testMD5(){

        String hashName = "md5";

        String pwd = "123";

        Object result = new SimpleHash(hashName, pwd, null, 2);

        System.out.println(result);


    }

}
