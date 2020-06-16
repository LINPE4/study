package cn.enjoy.product.listener;

import cn.enjoy.product.zk.ServiceRegister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Created by zw on 2018/6/26.
 */
public class InitListener implements ServletContextListener {



    @Override
    //容器初始化的时候会调用，重启tomcat
    public void contextInitialized(ServletContextEvent sce) {
            try {
                Properties properties =  new Properties();
                properties.load(InitListener.class.getClassLoader().getResourceAsStream("application.properties"));

                //获得IP
                String hostAddress = InetAddress.getLocalHost().getHostAddress();
                //获得端口

                int port = Integer.valueOf(properties.getProperty("server.port"));
                ServiceRegister.register(hostAddress,port);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
