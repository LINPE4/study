package cn.mldn.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberLoginAction {
	@RequestMapping("/hello") 
	public ModelAndView helloUrl() {
		return new ModelAndView("hello");
	}
	@RequestMapping("/loginUrl") 
	public ModelAndView loginUrl() {
		return new ModelAndView("login");
	}
	@RequestMapping("/unauthUrl")
	public ModelAndView unauthUrl() {
		return new ModelAndView("role");
	}
	@RequestMapping("/successUrl")
	public ModelAndView successUrl() {
		return new ModelAndView("welcome");
	}
}
