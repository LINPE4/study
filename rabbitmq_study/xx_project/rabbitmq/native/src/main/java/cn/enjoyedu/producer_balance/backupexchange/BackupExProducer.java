package cn.enjoyedu.producer_balance.backupexchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *类说明：生产者--绑定了一个备用交换器
 */
public class BackupExProducer {

    public final static String EXCHANGE_NAME = "main-exchange";
    public final static String BAK_EXCHANGE_NAME = "ae";

    public static void main(String[] args)
            throws IOException, TimeoutException {
        /**
         * 创建连接连接到RabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");

        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个信道
        Channel channel = connection.createChannel();
        //TODO
        // 声明备用交换器
        Map<String,Object> argsMap = new HashMap<String,Object>();
        argsMap.put("alternate-exchange",BAK_EXCHANGE_NAME);
        //主交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",
                false,false,argsMap);
        //备用交换器
        channel.exchangeDeclare(BAK_EXCHANGE_NAME,BuiltinExchangeType.FANOUT,
                true,false,null);

        //所有的消息
        String[] routekeys={"king","mark","james"};
        for(int i=0;i<3;i++){
            //每一次发送一条不同老师的消息
            String routekey = routekeys[i%3];

            // 发送的消息
            String message = "Hello World_"+(i+1);
            //参数1：exchange name
            //参数2：routing key
            channel.basicPublish(EXCHANGE_NAME, routekey,
                    null, message.getBytes());
            System.out.println(" [x] Sent '" + routekey +"':'"
                    + message + "'");
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }

}
