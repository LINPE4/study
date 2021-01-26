package com.xkcoding.helloworld.validate.exception;


/**
 * 公共异常基类
 * @author 0092397
 *
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 3631809131386665913L;
	
	public CommonException(){
	}
	
	public CommonException(Throwable e){
		super(e);
	}
	
	public CommonException(String errorCode){
		super(errorCode);
	}
	

	public CommonException(String errorCode,Throwable e){
		super(errorCode,e);
	}
	

}
