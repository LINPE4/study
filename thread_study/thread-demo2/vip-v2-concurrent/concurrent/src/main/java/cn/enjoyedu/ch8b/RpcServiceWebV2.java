package cn.enjoyedu.ch8b;

import cn.enjoyedu.ch8b.assist.Consts;
import cn.enjoyedu.ch8b.assist.CreatePendingDocs;
import cn.enjoyedu.ch8b.assist.SL_QuestionBank;
import cn.enjoyedu.ch8b.service.ProduceDocService;
import cn.enjoyedu.ch8b.vo.SrcDocVo;

import java.util.List;
import java.util.concurrent.*;

/**
 *类说明：题目的并行化，并引入缓存
 */
public class RpcServiceWebV2 {

    /*处理文档生成的线程池*/
    private static ExecutorService docMakeService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT*2);

    /*处理文档上传的线程池*/
    private static ExecutorService docUploadService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT*2);

    private static CompletionService<String> docCompletingServcie
            = new ExecutorCompletionService<String>(docMakeService);
    private static CompletionService<String> docUploadCompletingServcie
            = new ExecutorCompletionService<String>(docUploadService);


	public static void main(String[] args)
            throws ExecutionException, InterruptedException {
		int docCount = 60;
        System.out.println("题库开始初始化...........");
        SL_QuestionBank.initBank();
        System.out.println("题库初始化完成。");
        List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(docCount);
        long startTotal = System.currentTimeMillis();
        for(SrcDocVo doc:docList){
            docCompletingServcie.submit(new MakeDocTask(doc));
        }
        for(int i=0;i<docCount;i++){
            Future<String> future = docCompletingServcie.take();
            docUploadCompletingServcie.submit(new UploadTask(future.get()));
        }
        /*展示时间*/
        for(int i=0;i<docCount;i++){
            docUploadCompletingServcie.take().get();
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }

    //生成文档的工作任务
    private static class MakeDocTask implements Callable<String>{

        private SrcDocVo pendingDocVo;

        public MakeDocTask(SrcDocVo pendingDocVo) {
            this.pendingDocVo = pendingDocVo;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            String result = ProduceDocService.makeDocAsyn(pendingDocVo);
            System.out.println("文档"+result+"生成耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            return result;
        }
    }

    //上传文档的工作任务
    private static class UploadTask implements Callable<String>{

        private String fileName;

        public UploadTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            String result = ProduceDocService.upLoadDoc(fileName);
            System.out.println("已上传至["+result+"]耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            return result;
        }
    }
}
