package cn.mldn.realm;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import cn.mldn.service.IMemberService;
import cn.mldn.util.encrypt.MyPasswordEncrypt;
import cn.mldn.vo.Member;
// @Component
public class MemberRealm extends AuthorizingRealm {
	// @Resource
	private IMemberService memberService ;
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("********** 2、用户角色与权限：doGetAuthorizationInfo **********");
		String username = (String) principals.getPrimaryPrincipal() ;	// 取得用户登录名
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo() ;	// 定义授权信息的返回数据
		try {
			Map<String,Object> map = this.memberService.listAuthByMember(username) ;
			Set<String> allRoles = (Set<String>) map.get("allRoles") ;
			Set<String> allActions = (Set<String>) map.get("allActions") ;
			auth.setRoles(allRoles);// 所有的角色必须以Set集合的形式出现
			auth.setStringPermissions(allActions); 	// 所有的权限必须以Set集合的形式出现
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		return auth;
	} 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("********** 1、用户登录认证：doGetAuthenticationInfo() **********");
		// 1、登录认证的方法需要先执行，需要用他来判断登录的用户信息是否合法
		String username = (String) token.getPrincipal() ;	// 取得用户名
		// 需要通过用户名取得用户的完整信息，利用业务层操作
		Member vo = null ;
		try {
			vo = this.memberService.get(username) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (vo == null) {
			throw new UnknownAccountException("该用户名称不存在！") ;
		} else {	// 进行密码的验证处理
			String password = MyPasswordEncrypt.encryptPassword(new String((char[]) token.getCredentials()));
			// 将数据库中的密码与输入的密码进行比较，这样就可以确定当前用户是否可以正常登录
			if (vo.getPassword().equals(password)) {	// 密码正确
				AuthenticationInfo auth = new SimpleAuthenticationInfo(username, password, "memberRealm") ;
				return auth ;
			} else {
				throw new IncorrectCredentialsException("密码错误！") ;
			}
		}
	} 
}
