package com.xkcoding.helloworld;

import com.xkcoding.helloworld.beanRegister.ConsumerFactoryBean;
import com.xkcoding.helloworld.service.BaseService;
import com.xkcoding.helloworld.service.TestService;
import com.xkcoding.helloworld.service.impl.ServiceOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    TestService testService;

    @Autowired
    BaseService baseService;

    @Test
    public void test() {
        System.out.println(baseService.test("aa"));
    }

    @Test
    public void test2() {
        System.out.println(testService.sayHello());
    }

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAll() {
        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans)
        {
            System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
        }
    }

}
