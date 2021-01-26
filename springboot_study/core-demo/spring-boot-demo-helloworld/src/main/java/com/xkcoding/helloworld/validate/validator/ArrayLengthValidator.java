package com.xkcoding.helloworld.validate.validator;


import com.xkcoding.helloworld.validate.annotation.ArrayLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 校验长度实现
 * @作者 0195083
 * @创建时间 2017年5月27日 下午4:55:37
 * @TODO
 * @修改备注
 */
@SuppressWarnings("rawtypes")
public class ArrayLengthValidator implements
		ConstraintValidator<ArrayLength, List> {

	private int minValue;
	private int maxValue;

	@Override
	public void initialize(ArrayLength staticLengthValidator) {
		minValue = staticLengthValidator.min();
		maxValue = staticLengthValidator.max();
	}

	@Override
	public boolean isValid(List list, ConstraintValidatorContext arg1) {
		return null!=list?list.size() >= minValue &&list.size() <= maxValue:false;
	}

}
