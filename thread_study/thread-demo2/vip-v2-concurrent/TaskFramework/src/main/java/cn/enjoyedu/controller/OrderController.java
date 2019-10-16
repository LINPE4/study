package cn.enjoyedu.controller;

import cn.enjoyedu.framework.PendingJobPool;
import cn.enjoyedu.framework.vo.TaskResult;
import cn.enjoyedu.vo.OrderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 *类说明：用户注册的控制器
 */
@Controller
@RequestMapping(produces="text/html;charset=UTF-8")
public class OrderController {
    private static final String SUCCESS = "suc";
    private static final String FAILUER = "failure";

    @Autowired
    private PendingJobPool pool;
    @Autowired
    private OrderTask orderTask;

    @RequestMapping("/index")
    public String userReg(){
        return "order";
    }

    @RequestMapping("/submitOrder")
    @ResponseBody
    public String saveUser(@RequestParam("orderNumber")int orderNumber){
        pool.registerJob(OrderTask.JOB_NAME, orderNumber,
                orderTask,10);
        Random r = new Random();
        for(int i=0;i<orderNumber;i++) {
            pool.putTask(OrderTask.JOB_NAME, r.nextInt(1000));
        }
    	return SUCCESS;
    }

    @RequestMapping(value="/queryProgess")
    @ResponseBody
    public String queryProgess(){
        return pool.getTaskProgess(OrderTask.JOB_NAME);
    }

    @RequestMapping(value="/queryDetail")
    @ResponseBody
    public String queryDetail(){
        List<TaskResult<String>> taskDetail
                = pool.getTaskDetail(OrderTask.JOB_NAME);
        if(!taskDetail.isEmpty()) {
            //System.out.println(taskDetail);
            return taskDetail.toString();
        }
        return null;
    }


}
