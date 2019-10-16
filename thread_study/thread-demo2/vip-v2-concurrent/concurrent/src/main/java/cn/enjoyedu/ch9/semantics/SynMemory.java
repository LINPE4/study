package cn.enjoyedu.ch9.semantics;

import cn.enjoyedu.tools.SleepTools;

/**
 * 类说明：演示锁的内存语义
 */
public class SynMemory {
    private static boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            while(!ready){
                System.out.println("number = "+number);
            }
            System.out.println("number = "+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
