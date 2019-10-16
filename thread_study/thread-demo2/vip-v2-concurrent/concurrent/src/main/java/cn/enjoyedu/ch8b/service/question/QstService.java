package cn.enjoyedu.ch8b.service.question;

import cn.enjoyedu.ch8b.assist.SL_Busi;

import java.util.Random;

/**
 *类说明：单个题目处理的服务类
 */
public class QstService {
    /**
     * 对题目进行处理，如解析文本，下载图片等等工作
     * @param questionId 题目id
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId,String questionSrc){
        Random r = new Random();
        SL_Busi.buisness(450+r.nextInt(100));
        return "CompleteQuestion[id="+questionId
                +" content=:"+ questionSrc+"]";
    }
}
