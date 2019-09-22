package cn.mldn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestLoginDemo {
	public static void main(String[] args) {
		// 取得Factory接口对象，主要的目的是通过配置文件加载文件之中的信息，这些信息暂时不能够成为认证信息
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 取得里面所保存的所有的认证数据信息
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		// 利用一个专门的认证操作的处理类，实现认证处理的具体的实现
		SecurityUtils.setSecurityManager(securityManager);
		// 获取进行用户名和密码认证的接口对象
		Subject subject = SecurityUtils.getSubject() ;
		// 定义了一个Token，里面保存要登录的用户名和密码信息
		UsernamePasswordToken token = new UsernamePasswordToken("admin","hello") ;
		// 实现用户登录处理
		subject.login(token);
		System.out.println(subject.hasRole("member"));
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println(subject.isPermitted("member:add"));
	}
}
