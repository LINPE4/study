package cn.wolfcode.sso.filter;

import cn.wolfcode.sso.utils.HttpUtil;
import cn.wolfcode.sso.utils.SSOClientUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class SSOClientFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        //1.判断是否有局部会话
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if (isLogin != null && isLogin) {
            //有局部会话，放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 判断地址栏中是否有携带token参数
        String token = req.getParameter("token");
        if (StringUtils.isNoneBlank(token)) {
            // token 信息不为null，说明地址中包含了token，拥有令牌
            // 判断token信息是否有认证中心产生的
            String httpURL = SSOClientUtil.SERVER_URL_PREFIX + "/verify";
            Map<String, String> params = new HashMap<>();
            params.put("token", token);
            try {
                String isVerify = HttpUtil.sendHttpRequest(httpURL, params);
                if ("true".equals(isVerify)) {
                    //如果返回的字符串是true，说明这个token是由统一认证中心产生的
                    //创建局部会话
                    session.setAttribute("isLogin", true);
                    //放心该次的请求
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有局部会话，重定向到统一认证中心，检查是否有其他的系统已经登陆过
        SSOClientUtil.redirectToSSOURL(req, resp);
    }

    @Override
    public void destroy() {

    }
}
