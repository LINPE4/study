package cn.enjoyedu.cha;

import java.util.concurrent.TimeUnit;

/**
 * 类说明：线程组的使用
 */
public class ThreadGroupDemo {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group = new ThreadGroup("group1");
        Thread thread = new Thread(group, () -> {
            while(true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        //递归获取mainGroup中活跃线程的估计值
        System.out.println("activeCount = " + mainGroup.activeCount());
        //递归获mainGroup中的活跃子group
        System.out.println("activeGroupCount = " + mainGroup.activeGroupCount());
        //获取group的优先级, 默认为10
        System.out.println("getMaxPriority = " + mainGroup.getMaxPriority());
        //获取group的名字
        System.out.println("getName = " + mainGroup.getName());
        //获取group的父group, 如不存在则返回null
        System.out.println("getParent = " + mainGroup.getParent());
        //活跃线程信息全部输出到控制台
        mainGroup.list();
        System.out.println("----------------------------");
        //判断当前group是不是给定group的父线程, 如果两者一样,也会返回true
        System.out.println("parentOf = " + mainGroup.parentOf(group));
        System.out.println("parentOf = " + mainGroup.parentOf(mainGroup));

    }
}
