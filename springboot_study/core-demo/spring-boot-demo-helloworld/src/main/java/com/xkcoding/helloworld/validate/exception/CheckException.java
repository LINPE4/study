package com.xkcoding.helloworld.validate.exception;

/**
 * 包装需要检查的异常
 * 
 * @author 0092397
 *
 */
public class CheckException extends RuntimeException {

	private static final long serialVersionUID = 8571485544668091882L;

	public CheckException(Throwable e) {
		super(e);
	}

	public CheckException(String msg, Throwable e) {
		super(msg, e);
	}

}
