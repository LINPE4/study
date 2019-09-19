package cn.wolfcode.sso.utils;

import cn.wolfcode.sso.model.ClientInfoVO;

import java.util.*;

public class MockDatabaseUtil {
    public static Set<String> T_TOKEN = new HashSet<>();
    public static Map<String, List<ClientInfoVO>> T_CLIENT_INFO = new HashMap<>();
}
