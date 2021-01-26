/**
 * Project: spring-boot-demo-helloworld
 * File created at 2021/1/25 19:36
 * Copyright (c) 2018 linklogis.com all rights reserved.
 */
package com.xkcoding.helloworld.validate.config;

import com.xkcoding.helloworld.validate.CustomerValidatorAOP;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述
 *
 * @author linlipeng
 * @version 1.0
 * @type ValidatorConfig
 * @date 2021/1/25 19:36
 */
@Configuration
public class ValidatorConfig {
    //注意该地址为项目具体包地址  execution(* com.wu..facade..*Facade.*(..))
    public static final String traceExecution = "execution(* com.xkcoding.helloworld..provider..*Impl.*(..))";

    @Bean
    public CustomerValidatorAOP customerValidatorAOP() {
        CustomerValidatorAOP customerValidatorAOP = new CustomerValidatorAOP();
        return customerValidatorAOP;
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2(CustomerValidatorAOP interceptor) {
        System.out.println("defaultPointcutAdvisor2....");
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceExecution);

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
