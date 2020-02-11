package cn.enjoyedu.controller;

import cn.enjoyedu.producer.RocketMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@author King老师   享学课堂 https://enjoy.ke.qq.com
 *往期视频咨询芊芊老师  QQ：2130753077  VIP课程咨询 依娜老师QQ：2133576719
 *类说明：
 */
@Controller
@RequestMapping("/rocket")
public class RocketController {

	@Autowired
	@Qualifier("rocketMQProducer")
	private RocketMQProducer producer;

	/**
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("spring")
	public String queueSender(@RequestParam("message")String message){
		String opt="";
		try {
			Message msg = new Message("rocket-spring-topic", "TAG1", message.getBytes());
			SendResult result = producer.getDefaultMQProducer().send(msg);
			if(result.getSendStatus() !=null && result.getSendStatus().equals("SEND_OK")){
				opt = "suc";
			}else{
				opt = "err";
			}

		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}

	/**
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("springb")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			Message msg = new Message("rocket-spring-topic-b", "TAG1", message.getBytes());
			SendResult result = producer.getDefaultMQProducer().send(msg);
			System.out.println("SendStatus:"+result.getSendStatus());
			if(result.getSendStatus() !=null && result.getSendStatus().equals("SEND_OK")){
				opt = "suc";
			}else{
				opt = "err";
			}

		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}

}
