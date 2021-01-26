package com.xkcoding.helloworld.validate.aop;

import com.xkcoding.helloworld.validate.exception.CheckException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * 该类避免抛出throwable异常
 * 
 * @author 0092397
 *
 */
public abstract class CommonMethodInterceptor implements MethodInterceptor {
	
	/**
	 * 执行
	 * @param invocation
	 * @return
	 */
	protected Object proceed(MethodInvocation invocation) {
		try {
			return invocation.proceed();
		} catch (RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new CheckException(e);
		}
	}

}
