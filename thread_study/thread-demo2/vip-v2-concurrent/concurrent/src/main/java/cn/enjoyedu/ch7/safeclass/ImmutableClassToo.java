package cn.enjoyedu.ch7.safeclass;

import java.util.ArrayList;
import java.util.List;

/**
 * 类不可变--事实不可变
 */
public class ImmutableClassToo {
    private final List<Integer> list = new ArrayList<>(3);

    public ImmutableClassToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public boolean isContain(int i){
        return list.contains(i);
    }
}
