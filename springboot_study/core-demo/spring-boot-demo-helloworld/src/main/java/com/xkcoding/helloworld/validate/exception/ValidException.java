package com.xkcoding.helloworld.validate.exception;

/**
 * 校验框架抛出异常
 * @author 0092397
 *
 */
public class ValidException extends CommonException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1553558090516970438L;

	public ValidException() {
	}

	public ValidException(Throwable e) {
		super(e);
	}

	public ValidException(String errorCode) {
		super(errorCode);
	}


	public ValidException(String errorCode, Throwable e) {
		super(errorCode, e);
	}

}
