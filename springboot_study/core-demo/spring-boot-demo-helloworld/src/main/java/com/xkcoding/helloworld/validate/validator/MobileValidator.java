package com.xkcoding.helloworld.validate.validator;


import com.xkcoding.helloworld.validate.annotation.ArrayLength;
import com.xkcoding.helloworld.validate.annotation.Mobile;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 校验长度实现
 * @作者 0195083
 * @创建时间 2017年5月27日 下午4:55:37
 * @TODO
 * @修改备注
 */
@SuppressWarnings("rawtypes")
public class MobileValidator implements
		ConstraintValidator<Mobile, String> {

	private Pattern pattern;

	@Override
	public void initialize(Mobile constraintAnnotation) {
		this.pattern = Pattern.compile(constraintAnnotation.pattern());
	}

	@Override
	public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isEmpty(mobile)) {
			return true;
		}
		System.out.println("mobile:" + mobile);
		Matcher m = pattern.matcher(mobile);
		System.out.println(m.matches());
		return m.matches();
	}

}
