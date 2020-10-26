package com.xkcoding.helloworld;

import com.xkcoding.helloworld.proxy.mapper.CalculateService;
import com.xkcoding.helloworld.proxy.mapper.HelloService;
import com.xkcoding.helloworld.proxy.service.PeterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests2 {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    HelloService helloService;

    @Autowired
    CalculateService calculateService;

//    @Autowired
//    List<PeterService> peterServices;

    @Resource(name = "peter")
    PeterService peterService;

    @Resource(name = "peterServiceImplProxy")
    PeterService peterServiceProxy;

    @Test
    public void test() {
//        String reu = helloService.getList("aa", "bb");
//        System.out.println(reu);

        peterService.test("peter");
        System.out.println("--------");
        peterServiceProxy.test("peter proxy");
    }


}
