package cn.enjoyedu.consumer_balance.qos;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author 【享学课堂】 King老师
 * 需要往期视频的同学加QQ：2068425757（肉兰老师）
 * 需要咨询VIP课程的同学加QQ：1011843464 （依娜老师）
 *
 * 批量确认 -----消费者
 */
public class BatchAckConsumer extends DefaultConsumer {
    //计数，第多少条
    private  int meesageCount =0;
    public BatchAckConsumer(Channel channel) {
        super(channel);
        System.out.println("批量消费者启动了......");
    }

    @Override
    public void handleDelivery(String consumerTag,
                               Envelope envelope,
                               AMQP.BasicProperties properties,
                               byte[] body) throws IOException {
        //把消息体拉出来
        String message = new String(body,"UTF-8");

        System.out.println("批量消费者---Received["+envelope.getRoutingKey()
                +"]"+message);
        meesageCount++;
        //批量确认 50一批
        if(meesageCount %50 ==0){
           this.getChannel().basicAck(envelope.getDeliveryTag(),true);
            System.out.println("批量消息费进行消息的确认------------");
        }
        if(message.equals("stop")){ //如果是最后一条消息，则把剩余的消息都进行确认
            this.getChannel().basicAck(envelope.getDeliveryTag(),true);
            System.out.println("批量消费者进行最后业务消息的确认---------");
        }
    }
}
