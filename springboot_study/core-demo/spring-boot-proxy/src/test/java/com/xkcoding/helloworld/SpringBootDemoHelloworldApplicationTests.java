package com.xkcoding.helloworld;

import com.xkcoding.helloworld.proxy.mapper.CalculateService;
import com.xkcoding.helloworld.proxy.mapper.HelloService;
import com.xkcoding.helloworld.proxy.service.FruitsService;
import com.xkcoding.helloworld.proxy.service.PeterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests {

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


    @Autowired
    FruitsService bananaService;

    @Test
    public void test() {
//        String reu = helloService.getList("aa", "bb");
//        System.out.println(reu);

        peterService.test("peter");
        System.out.println("");
        peterServiceProxy.test("peter proxy");
    }

    /**
     * 代码注入的是Apple
     * 在容器创建后重新注入了Banana
     */
    @Test
    public void test3() {
        System.out.println(bananaService.desc(2));
    }

    @Test
    public void test2() {
        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans)
        {
            System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
        }
    }

}
