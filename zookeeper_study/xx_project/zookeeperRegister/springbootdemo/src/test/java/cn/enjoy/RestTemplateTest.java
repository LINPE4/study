package cn.enjoy;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    @Resource
    private RestTemplate template;

    @Test
    public void test() throws  Exception {

        ResponseEntity<Object> forEntity = template.getForEntity("http://localhost:8080/getProduct/1", Object.class);
        System.out.println(forEntity.getBody());
    }


    @Test
    public void test2() throws  Exception {
        List<String> urls = new ArrayList<>();
        urls.add("http://localhost:8080/getProduct/1");
        urls.add("http://localhost:8081/getProduct/1");

        ResponseEntity<Object> forEntity = template.getForEntity(urls.get(new Random().nextInt(2)), Object.class);
        System.out.println(forEntity.getBody());
    }


}
