package cn.wolfcode.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SSOServerController {

    /**
     * 检验是否登陆
     * @param redirectUrl
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("checkLogin")
    public String checkLogin(String redirectUrl, HttpSession session, Model model) {
        //1. 判断是否有全局会话
        String token = (String)session.getAttribute("token");
        if (StringUtils.isEmpty(token)) {
            //表示没有全局会话
            //跳转到统一认证中心的登陆页面
            model.addAttribute("redirectUrl", redirectUrl);
            return "login";
        } else{
            return "";
        }

    }
}
