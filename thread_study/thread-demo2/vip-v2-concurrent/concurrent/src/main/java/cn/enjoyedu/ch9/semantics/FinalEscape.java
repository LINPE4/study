package cn.enjoyedu.ch9.semantics;

/**
 * 类说明：不能让final引用从构造方法中溢出
 */
public class FinalEscape {
    final int i;
    static FinalEscape obj;

    public FinalEscape() {
        i = 10;         //写final域
        obj = this;     //this引用溢出
    }

    public static void writer(){
        new FinalEscape();
    }

    public static void reader(){
        if(obj!=null){          //3
            int temp = obj.i;   //4
        }
    }
}
