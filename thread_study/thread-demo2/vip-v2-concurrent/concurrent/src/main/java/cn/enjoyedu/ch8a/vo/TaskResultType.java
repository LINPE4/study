package cn.enjoyedu.ch8a.vo;

/**
 *类说明：方法本身运行是否正确的结果类型
 */
public enum TaskResultType {
	Success,/*方法执行完成，业务结果也正确*/
	Failure,/*方法执行完成，业务结果错误*/
	Exception/*方法执行抛出了异常*/
}
