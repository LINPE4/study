package cn.enjoyedu.ch8b.vo;

import java.util.concurrent.Future;

/**
 * 类说明：并发处理返回的题目结果实体类
 */
public class TaskResultVo {
    private final String questionDetail;
    private final Future<QuestionInCacheVo> questionFuture;

    public TaskResultVo(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture =null;
    }

    public TaskResultVo(Future<QuestionInCacheVo> questionFuture) {
        this.questionFuture = questionFuture;
        this.questionDetail = null;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public Future<QuestionInCacheVo> getQuestionFuture() {
        return questionFuture;
    }
}
