package cn.enjoyedu.ch8b.assist;

/**
 *类说明：系统常量类
 */
public class Consts {
	
    //取得本地机器cpu数量
    public final static int THREAD_COUNT
            = Runtime.getRuntime().availableProcessors();
	
  //每个文档中题目的个数
    public static final int QUESTION_COUNT_IN_DOC = 60;
  //题库大小
    public static final int SIZE_OF_QUESTION_BANK = 2000;


}
