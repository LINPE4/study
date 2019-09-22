package cn.mldn.action;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.service.IEmpService;
@Controller 
@RequestMapping("/pages/emp/*")
public class EmpAction {
	@Resource
	private IEmpService empService ;
	@RequestMapping("add")
	public ModelAndView add() {
		// this.empService.add();
		System.out.println("SESSION ID = " + SecurityUtils.getSubject().getSession().getId());
		System.out.println("用户名：" + SecurityUtils.getSubject().getPrincipal());
		System.out.println("HOST：" + SecurityUtils.getSubject().getSession().getHost());
		System.out.println("TIMEOUT ：" + SecurityUtils.getSubject().getSession().getTimeout());
		System.out.println("START：" + SecurityUtils.getSubject().getSession().getStartTimestamp());
		System.out.println("LAST：" + SecurityUtils.getSubject().getSession().getLastAccessTime()); 
		return null ;
	}
	@RequestMapping("edit")
	public ModelAndView edit() {
		SecurityUtils.getSubject().getSession().touch(); 	// 更新会话
		SecurityUtils.getSubject().getSession().stop(); 
		// this.empService.edit();
		return null ;
	}
	@RequestMapping("remove")
	public ModelAndView remove() {
		// this.empService.remove();
		SecurityUtils.getSubject().logout(); 
		return null ;
	} 
	@RequestMapping("list")
	public ModelAndView list() {
		this.empService.list(); 
		return null ;
	}
}
