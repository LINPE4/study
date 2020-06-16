package cn.enjoy;


import cn.enjoy.init.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SbApp {
    public static void main(String[] args) throws  Exception {
        ConfigurableApplicationContext run = SpringApplication.run(SbApp.class, args);
        Thread.sleep(10000);
        System.out.println("执行到这一行");
        run.close();
    }

    @Bean
    //启动监听起，当tomcat启动的时候，会调用InitListener
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return  servletListenerRegistrationBean;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
