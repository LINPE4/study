package com.xkcoding.helloworld;

import com.xkcoding.helloworld.model.Address;
import com.xkcoding.helloworld.model.User;
import com.xkcoding.helloworld.provider.UserProvider;
import com.xkcoding.helloworld.proxy.mapper.CalculateService;
import com.xkcoding.helloworld.proxy.mapper.HelloService;
import com.xkcoding.helloworld.proxy.service.PeterService;
import com.xkcoding.helloworld.validate.CustomerValidatorAOP;
import com.xkcoding.helloworld.validate.inter.QueryOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests3 {

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private Validator validator;

    @Test
    public void test() {
        User user = new User();
        user.setId(111);
        user.setUserName("peter");
        user.setMobile("111");
        Address address = new Address();
        user.setAddress(address);
        userProvider.addUser(user);
    }

    @Test
    public void testList() {
        User user = new User();
        user.setId(111);
//        user.setUserName("peter");

        Address address = new Address();
        user.setAddress(address);
        userProvider.addUserList(Arrays.asList(user));
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId(111);
        user.setUserName("peter");

//        Address address = new Address();
//        user.setAddress(address);

        Address address1 = new Address();
        user.setAddressList(Arrays.asList(address1));
        Set<ConstraintViolation<User>> validate = validator.validate(user, QueryOne.class);
        System.out.println(validate);
        validate.forEach(e-> {
            System.out.println(e.getMessage());
        });

    }


}
