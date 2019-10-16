package cn.enjoyedu.ch7.safeclass.safepublish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 安全的发布
 */
public class SafePublishToo {
    private List<Integer> list
            = Collections.synchronizedList(new ArrayList<>(3));

    public SafePublishToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public List getList() {
        return list;
    }

    public static void main(String[] args) {
        SafePublishToo safePublishToo = new SafePublishToo();
        List<Integer> list = safePublishToo.getList();
        System.out.println(list);
        list.add(4);
        System.out.println(list);
        System.out.println(safePublishToo.getList());
    }
}
