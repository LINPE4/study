package com.xkcoding.helloworld.beanRegister;

import com.xkcoding.helloworld.service.BaseService;
import com.xkcoding.helloworld.service.impl.ServiceOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 单元测试
 * 
 * @author 0092397
 *     spring 启动的时候从容器注册baseservice（接口）实列，底下生成代理类
 */
@Configuration
public class MockConsumer implements BeanDefinitionRegistryPostProcessor {

    Logger logger = LoggerFactory.getLogger(MockConsumer.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.getPropertyValues().add("mockInterface", "com.xkcoding.helloworld.service.BaseService");
            beanDefinition.setBeanClass(ConsumerFactoryBean.class);
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);

            BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, "baseService");
            BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(ServiceOne.class.getSimpleName());
    }


}
