/**
 * Project: spring-boot-demo
 * File created at 2020/7/3 18:04
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.proxy.handler;

import com.xkcoding.helloworld.proxy.service.FruitsService;
import com.xkcoding.helloworld.proxy.service.multi.BananaServiceImpl;
import com.xkcoding.helloworld.proxy.service.proxy.ServiceImplProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用于Spring动态注入自定义接口
 * @author lichuang
 */
@Component
public class ServiceBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor,ResourceLoaderAware,ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //这里一般我们是通过反射获取需要代理的接口的clazz列表
        //比如判断包下面的类，或者通过某注解标注的类等等
        Set<Class<?>> beanClazzs = scannerPackages("com.xkcoding.helloworld.proxy.mapper");
        for (Class beanClazz : beanClazzs) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();

            //在这里，我们可以给该对象的属性注入对应的实例。
            //比如mybatis，就在这里注入了dataSource和sqlSessionFactory，
            // 注意，如果采用definition.getPropertyValues()方式的话，
            // 类似definition.getPropertyValues().add("interfaceType", beanClazz);
            // 则要求在FactoryBean（本应用中即ServiceFactory）提供setter方法，否则会注入失败
            // 如果采用definition.getConstructorArgumentValues()，
            // 则FactoryBean中需要提供包含该属性的构造方法，否则会注入失败
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClazz);

            //注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
            // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
            // 其返回的是该工厂Bean的getObject方法所返回的对象。
            definition.setBeanClass(ServiceFactory.class);

            //这里采用的是byType方式注入，类似的还有byName等
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(beanClazz.getSimpleName(), definition);
        }

        // 生成代理类并注入
        Set<Class<?>> beanProxyClazzs = scannerPackages("com.xkcoding.helloworld.proxy.service.impl");
        for (Class beanClazz : beanProxyClazzs) {
            System.out.println(beanClazz);

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
            GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
            definition.getConstructorArgumentValues().addGenericArgumentValue(applicationContext.getBean(beanClazz));
            definition.setBeanClass(ServiceImplProxyFactory.class);
            //这里采用的是byType方式注入，类似的还有byName等
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            // 重新注入代理对象
            String proxyBeanName = toLowerCaseFirstOne(beanClazz.getSimpleName() + "Proxy");
            System.out.println("proxyBeanName: " + proxyBeanName);
            registry.registerBeanDefinition(proxyBeanName, definition);
        }

        // 删除原有实现类，重新注入另外的实现类
        FruitsService fBean = applicationContext.getBean(FruitsService.class);
        System.out.println("fruits: " + fBean.desc(2));
        // 输出FruitsService的Bean基本信息
        System.out.println(fBean.getClass());//class yjmyzz.FooA
        String beanName = applicationContext.getBeanNamesForType(FruitsService.class)[0];
        System.out.println(beanName);//appleServiceImpl
        System.out.println(applicationContext.isSingleton(beanName));

        //销毁FruitsService实例
        removeBean(registry, beanName);
        System.out.println(applicationContext.containsBean(beanName));//false
        System.out.println("------------");

        //注入新Bean
        beanName = "bananaService";
        addBean(registry, beanName, BananaServiceImpl.class);

        //取出新实例
        fBean = applicationContext.getBean(beanName, FruitsService.class);
        System.out.println("fruits: " + fBean.desc(2));
    }

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private MetadataReaderFactory metadataReaderFactory;

    /**
     * 根据包路径获取包及子包下的所有类
     * @param basePackage basePackage
     * @return Set<Class<?>> Set<Class<?>>
     */
    private Set<Class<?>> scannerPackages(String basePackage) {
        Set<Class<?>> set = new LinkedHashSet<>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
            resolveBasePackage(basePackage) + '/' + DEFAULT_RESOURCE_PATTERN;
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(className);
                        set.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    protected String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(this.getEnvironment().resolveRequiredPlaceholders(basePackage));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private ResourcePatternResolver resourcePatternResolver;

    private ApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

    //首字母转小写
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    /**
     * 向容器中动态添加Bean
     *
     * @param beanName
     * @param beanClass
     */
    static void addBean(BeanDefinitionRegistry beanDefReg, String beanName, Class beanClass) {
        BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        BeanDefinition beanDef = beanDefBuilder.getBeanDefinition();
        if (!beanDefReg.containsBeanDefinition(beanName)) {
            beanDefReg.registerBeanDefinition(beanName, beanDef);
        }
    }

    /**
     * 从容器中移除Bean
     *
     * @param beanName
     */
    static void removeBean(BeanDefinitionRegistry beanDefReg, String beanName) {
        BeanDefinition beanDefinition = beanDefReg.getBeanDefinition(beanName);
        beanDefReg.removeBeanDefinition(beanName);
    }


}
