package cn.wolfcode.sso.filter;

import cn.wolfcode.sso.utils.SSOClientUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        }
        // 没有局部会话，重定向到统一认证中心，检查是否有其他的系统已经登陆过
        SSOClientUtil.redirectToSSOURL(req, resp);
    }

    @Override
    public void destroy() {

    }
}
