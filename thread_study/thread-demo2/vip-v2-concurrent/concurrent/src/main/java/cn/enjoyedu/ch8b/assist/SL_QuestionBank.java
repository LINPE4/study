package cn.enjoyedu.ch8b.assist;

import cn.enjoyedu.ch8b.vo.QuestionInDBVo;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *@author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 *
 *类说明：模拟存储中数据库中的题库数据
 */
public class SL_QuestionBank {

    //题库数据存储
    private static ConcurrentHashMap<Integer,QuestionInDBVo> questionBankMap
            = new ConcurrentHashMap<>();
    //定时任务池，负责定时更新题库数据
    private static ScheduledExecutorService updateQuestionBank
            = new ScheduledThreadPoolExecutor(1);

    //初始化题库
    public static void initBank(){
        for(int i=0;i<Consts.SIZE_OF_QUESTION_BANK;i++){
            String questionContent = makeQuestionDetail(800);
            questionBankMap.put(i,new QuestionInDBVo(i,
            		questionContent,EncryptUtils.EncryptBySHA1(questionContent)));
        }
        updateQuestionTimer();
    }

    //生成随机字符串，代表题目的实际内容,length表示题目的长度
    private static String makeQuestionDetail(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //获得题目，我们假设一次数据库的读耗时在一般在20ms左右，所以休眠20ms
    public static QuestionInDBVo getQuetion(int i) {
        SL_Busi.buisness(20);
        return questionBankMap.get(i);
    }

    //更新题库的定时任务
    private static class UpdateBank implements Runnable{

        @Override
        public void run() {
            Random random = new Random();
            int questionId = random.nextInt(Consts.SIZE_OF_QUESTION_BANK);
            String questionContent = makeQuestionDetail(700);
            questionBankMap.put(questionId,new QuestionInDBVo(questionId,
                    questionContent,EncryptUtils.EncryptBySHA1(questionContent)));
            //System.out.println("题目【"+questionId+"】被更新！！");
        }
    }

    //定期更新题库数据
    private static void updateQuestionTimer(){
        System.out.println("开始定时更新题库..........................");
        updateQuestionBank.scheduleAtFixedRate(new UpdateBank(),
                15,5, TimeUnit.SECONDS);
    }

    //获得题目Sha值，我们假设一次数据库的读耗时在一般在10ms左右，所以休眠10ms
    public static String getQuestionSha(int i) {
        SL_Busi.buisness(20);
        return questionBankMap.get(i).getSha();
    }

}
