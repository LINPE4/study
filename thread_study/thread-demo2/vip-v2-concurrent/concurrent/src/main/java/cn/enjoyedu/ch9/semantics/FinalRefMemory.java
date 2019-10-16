package cn.enjoyedu.ch9.semantics;

/**
 * 类说明：
 */
public class FinalRefMemory {
    final int[] intArray;       // final 是引用类型
    static FinalRefMemory obj;

    public FinalRefMemory() {   // 构造函数
        intArray = new int[1];         // 1
        intArray[0] = 1;               // 2
    }

    public static void writerOne() {   // 写线程A执行
        obj = new FinalRefMemory();// 3
    }

    public static void writeTwo() {     // 写线程B执行
        obj.intArray[0] = 2;            // 4
    }

    public static void reader() {       // 读线程C执行
        if (obj != null) {              // 5
            int temp1 = obj.intArray[0];// 6
        }
    }

}
