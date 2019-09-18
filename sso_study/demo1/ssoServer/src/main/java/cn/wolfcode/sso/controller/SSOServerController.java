package cn.wolfcode.sso.controller;

import cn.wolfcode.sso.utils.MockDatabaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    @RequestMapping("/login")
    public String login(String username, String password, String redirectUrl, HttpSession session, Model model) {
        if ("zhangsan".equals(username) && "666".equals(password)) {
            //账号密码匹配
            // 1. 创建令牌信息
            String token = UUID.randomUUID().toString();
            // 2. 创建全局的会话，把令牌信息放入会话中
            session.setAttribute("token", token);
            // 3. 需要把令牌信息放到数据库中
            MockDatabaseUtil.T_TOKEN.add(token);
            // 4. 重定向到redirectUrl,把令牌信息带上
            model.addAttribute("token", token);
            return "redirect:" + redirectUrl;
        }
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

    @RequestMapping("verify")
    @ResponseBody
    public String verifyToken(String token) {
        if (MockDatabaseUtil.T_TOKEN.contains(token)) {
            return "true";
        }
        return "false";
    }
}
