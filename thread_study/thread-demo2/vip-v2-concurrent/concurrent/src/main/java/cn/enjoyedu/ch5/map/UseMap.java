package cn.enjoyedu.ch5.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 类说明：ConcurrentHashMap的使用
 */
public class UseMap {
    public static void main(String[] args) {
        ConcurrentHashMap<KeyVo,String> map = new ConcurrentHashMap<>();
        KeyVo keyVo = new KeyVo(1,"A");
        System.out.println("put不存在的值------");
        System.out.println(map.put(keyVo,"AA"));
        System.out.println(map.get(keyVo));

        System.out.println("put已存在的key-------------");
        System.out.println(map.put(keyVo,"BB"));
        System.out.println(map.get(keyVo));

        System.out.println("putIfAbsent已存在的key-------------");
        System.out.println(map.putIfAbsent(keyVo,"CC"));
        System.out.println(map.get(keyVo));

        System.out.println("putIfAbsent不存在的key-------------");
        KeyVo keyVo2 = new KeyVo(2,"B");
        System.out.println(map.putIfAbsent( keyVo2,"CC"));
        System.out.println(map.get(keyVo2));

        ConcurrentHashMap<KeyVo,String> map2
                = new ConcurrentHashMap<KeyVo,String>(2,
                0.75f,16);

//putIfAbsent类似的业务流程:
//        synchronized (map){
//            if(map.get(key)==null){
//                map.put(key.value)
//            }else{
//                return map.get(key);
//            }
//        }

    }
}
