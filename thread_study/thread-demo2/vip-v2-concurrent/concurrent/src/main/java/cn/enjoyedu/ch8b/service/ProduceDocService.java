package cn.enjoyedu.ch8b.service;

import cn.enjoyedu.ch8b.assist.SL_Busi;
import cn.enjoyedu.ch8b.service.question.ParallelQstService;
import cn.enjoyedu.ch8b.service.question.SingleQstService;
import cn.enjoyedu.ch8b.vo.SrcDocVo;
import cn.enjoyedu.ch8b.vo.TaskResultVo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 *类说明：处理文档的服务
 */
public class ProduceDocService {

    /**
     * 上传文档到网络
     * @param docFileName 实际文档在本地的存储位置
     * @return 上传后的网络存储地址
     */
    public static String upLoadDoc(String docFileName){
        Random r = new Random();
        SL_Busi.buisness(9000+r.nextInt(400));
        return "http://www.xxxx.com/file/upload/"+docFileName;
    }

    /**
     * 将待处理文档处理为本地实际文档
     * @param pendingDocVo 待处理文档
     * @return 实际文档在本地的存储位置
     */
    public static String makeDoc(SrcDocVo pendingDocVo){
        System.out.println("开始处理文档："+ pendingDocVo.getDocName());
        StringBuffer sb = new StringBuffer();
        for(Integer questionId: pendingDocVo.getQuestionList()){
            sb.append(SingleQstService.makeQuestion(questionId));
        }
        return "complete_"+System.currentTimeMillis()+"_"
        	+ pendingDocVo.getDocName()+".pdf";
    }

    /*异步生成题目*/
    public static String makeDocAsyn(SrcDocVo pendingDocVo) throws ExecutionException, InterruptedException {
        System.out.println("开始处理文档："+ pendingDocVo.getDocName());
        /*每个题目的处理结果*/
        Map<Integer,TaskResultVo> qstResultMap = new HashMap<>();
        for(Integer questionId:pendingDocVo.getQuestionList()){
            qstResultMap.put(questionId,
                    ParallelQstService.makeQuestion(questionId));
        }

        StringBuffer sb = new StringBuffer();
        for(Integer questionId:pendingDocVo.getQuestionList()){
            TaskResultVo taskResultVo = qstResultMap.get(questionId);
            sb.append(taskResultVo.getQuestionDetail()==null?
                    taskResultVo.getQuestionFuture().get().getQuestionDetail()
                    :taskResultVo.getQuestionDetail());

        }
        return "complete_"+System.currentTimeMillis()+"_"
                + pendingDocVo.getDocName()+".pdf";
    }
}
