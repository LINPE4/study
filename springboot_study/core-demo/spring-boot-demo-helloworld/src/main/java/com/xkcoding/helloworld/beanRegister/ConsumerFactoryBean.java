package com.xkcoding.helloworld.beanRegister;

import com.xkcoding.helloworld.beanRegister.dynamic.LisonCompany;
import com.xkcoding.helloworld.service.BaseService;
import com.xkcoding.helloworld.service.impl.ServiceOne;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
 * @author 0092397
 *
 * @param <T>
 */
public class ConsumerFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mockInterface;

    private volatile T object;

    @Override
    public T getObject() throws Exception {
        if (object == null) {
            synchronized (mockInterface) {
                if (object == null) {
//                    object = PowerMockito.mock(mockInterface);
//                    LisonCompany lisonComp = new LisonCompany();
//                    BaseService t = new ServiceOne();
//                    lisonComp.setFactory(t);
//                    object = (T) lisonComp.getProxyInstance();
//                    object = (T) new ServiceOne();

                    LisonCompany lisonComp = new LisonCompany();
                    lisonComp.setFactory(mockInterface);
                    object = (T) lisonComp.getProxyInstance();
                }
            }
        }
        return object;
    }

    @Override
    public Class<?> getObjectType() {
        return mockInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<T> getMockInterface() {
        return mockInterface;
    }

    public void setMockInterface(Class<T> mockInterface) {
        this.mockInterface = mockInterface;
    }
}
