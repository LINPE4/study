package com.xkcoding.helloworld.validate;

import com.xkcoding.helloworld.validate.annotation.Validate;
import com.xkcoding.helloworld.validate.aop.CommonMethodInterceptor;
import com.xkcoding.helloworld.validate.exception.ValidException;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;


/**
 * 校验aop
 * 
 * @author 0092397
 *
 */
public class CustomerValidatorAOP extends CommonMethodInterceptor {

	@Autowired
	private Validator validator;

	/**
	 * 获取组
	 * 
	 * @param annotations
	 * @return
	 */
	private Class<?>[] getGroups(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (Validate.class.isInstance(annotation)) {
				Validate validate = (Validate) annotation;
				return validate.value();
			}
		}
		return null;
	}

	/**
	 * 判断是否校验
	 * 
	 * @param annotations
	 * @return
	 */
	private boolean hasRequestBodyAndValidAnnotations(Annotation[] annotations) {
		if (annotations.length < 1) {
			return false;
		}

		for (Annotation annotation : annotations) {
			if (Validate.class.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验参数
	 * 
	 * @param arg
	 * @param groups
	 */
	private void validateArg(Object arg, Class<?>[] groups) {
		Set<ConstraintViolation<Object>> set = null;
		if (List.class.isAssignableFrom(arg.getClass())) {
			@SuppressWarnings("unchecked")
			List<Object> argList = (List<Object>) arg;
			if (!CollectionUtils.isEmpty(argList)) {
				for (int i = 0; i < argList.size()
						&& CollectionUtils.isEmpty(set); i++) {
					set = validator.validate(argList.get(i), groups);
				}
			}
		} else {
			set = validator.validate(arg, groups);
		}
		StringBuilder errorStr = new StringBuilder();
		for (ConstraintViolation<Object> constraint : set) {
			String message = constraint.getMessage();
			errorStr.append(message).append("<br/>");
		}

		if (errorStr.length() > 0) {
			throw new ValidException(errorStr.toString());
		}
	}

	/**
	 * aop方法
	 */
	@Override
	public Object invoke(MethodInvocation invocation) {
		Method method = invocation.getMethod();
		Annotation[][] argAnnotations = method.getParameterAnnotations();
		System.out.println("fk");
		System.out.println(argAnnotations[0]);
		Object[] args = invocation.getArguments();

		for (int i = 0; i < args.length; i++) {
			if (hasRequestBodyAndValidAnnotations(argAnnotations[i])) {
				Class<?>[] groups = getGroups(argAnnotations[i]);
				validateArg(args[i], groups);
			}
		}
		return proceed(invocation);
	}

}
