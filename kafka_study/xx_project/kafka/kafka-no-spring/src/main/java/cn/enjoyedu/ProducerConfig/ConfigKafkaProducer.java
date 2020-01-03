package cn.enjoyedu.ProducerConfig;

import cn.enjoyedu.config.BusiConst;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author King老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：kafka生产者
 */
public class ConfigKafkaProducer {

    public static void main(String[] args) {
        //TODO 生产者三个属性必须指定(broker地址清单、key和value的序列化器)
        Properties properties = new Properties();
        properties.put("bootstrap.servers","127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //TODO 更多发送配置（重要的）
        properties.put("acks","1"); //ack 0,1,all
        properties.put("batch.size",16384); // 一个批次可以使用的内存大小 缺省16384(16k)
        properties.put("linger.ms",0L); // 指定了生产者在发送批次前等待更多消息加入批次的时间,  缺省0  50ms
        properties.put("max.request.size",1 * 1024 * 1024); // 控制生产者发送请求最大大小,默认1M （这个参数和Kafka主机的message.max.bytes 参数有关系）

        //TODO 更多发送配置（非重要的）
        properties.put("buffer.memory",32 * 1024 * 1024L);//生产者内存缓冲区大小
        properties.put("retries",0); //重发消息次数
        properties.put("request.timeout.ms",30 * 1000);//客户端将等待请求的响应的最大时间 默认30秒
        properties.put("max.block.ms",60*1000);//最大阻塞时间，超过则抛出异常 缺省60000ms

        properties.put("compression.type","none"); // 于压缩数据的压缩类型。默认是无压缩 ,none、gzip、snappy


        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        try {
            ProducerRecord<String,String> record;
            try {
                //TODO发送4条消息
                for(int i=0;i<4;i++){
                    record = new ProducerRecord<String,String>(BusiConst.HELLO_TOPIC, String.valueOf(i),"lison");
                    producer.send(record);
                    System.out.println(i+"，message is sent");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            producer.close();
        }
    }


}
