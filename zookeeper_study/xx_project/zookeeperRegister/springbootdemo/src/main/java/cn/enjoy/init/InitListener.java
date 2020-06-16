package cn.enjoy.init;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by zw on 2018/6/26.
 */
public class InitListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("容器初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("容器销毁了");
    }

}
