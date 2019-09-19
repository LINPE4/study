package cn.wolfcode.sso.listener;

import cn.wolfcode.sso.model.ClientInfoVO;
import cn.wolfcode.sso.utils.HttpUtil;
import cn.wolfcode.sso.utils.MockDatabaseUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

public class MySessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String token = (String) session.getAttribute("token");
        MockDatabaseUtil.T_TOKEN.remove(token);
        List<ClientInfoVO> removeList = MockDatabaseUtil.T_CLIENT_INFO.remove(token);
        try {
            for (ClientInfoVO vo: removeList) {
                HttpUtil.sendHttpRequest(vo.getClientUrl(), vo.getJessionid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
