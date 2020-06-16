package cn.enjoy.order.utils;

import org.springframework.util.CollectionUtils;

import java.util.Random;

/**
 * Created by VULCAN on 2018/7/25.
 */

//实现了一个随机的算法
public class RamdomLoadBalance extends  LoadBalance {
    @Override
    public String choseServiceHost() {
        String result = "";

        //SERVICE_LIST 产品 服务的列表
        if(!CollectionUtils.isEmpty(SERVICE_LIST)) {
           // 192.168.30.3:8083
            //192.168.30.3:8084  2
            //index 0,1
            //传入一个种子
            int index = new Random().nextInt(SERVICE_LIST.size());
            result = SERVICE_LIST.get(index);
        }
        return result ;
    }
}
