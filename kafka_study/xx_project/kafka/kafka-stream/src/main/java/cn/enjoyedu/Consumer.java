package cn.enjoyedu;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * 需要往期视频的同学加QQ：2070155495（七七老师）
 * 需要咨询VIP课程的同学加QQ：1399484076（芊芊老师）
 * 消费者（消费者类）
 */
public class Consumer {
    //kafka消费者对象
    private static KafkaConsumer<String,String> consumer = null;

    public static void main(String[] args) {
        /*发送配置的实例*/
        Properties properties = new Properties();
        /*broker的地址清单*/
        properties.put("bootstrap.servers","localhost:9092");
        /*key的反序列化器*/
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        /*value的反序列化器*/
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        /*每次获取数据从头开始*/
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        /*消费者的群组id*/
        properties.put("group.id","king");
        /*消息者*/
        consumer = new KafkaConsumer<String, String>(properties);
        try {
            long startTime = System.currentTimeMillis();
            //订阅 主题 mall
            consumer.subscribe(Collections.singletonList("phone"));
            while(true){  //无限循环,
                ConsumerRecords<String, String> records = consumer.poll(500);
                if(records.count() ==0) break;
                for(ConsumerRecord<String, String> record:records){
                    System.out.println(String.format(
                            "秒杀业务处理:主题：%s，序号(偏移量)：%d，商品列表：%s",
                            record.topic(),record.offset(),record.value()));
                    Thread.sleep(10); //1秒钟处理100条(一订单包含多个商品)
                }
            }
            long end = System.currentTimeMillis();
            float seconds = (end - startTime) / 1000F;
            System.out.println("消费者消耗时间:"+Float.toString(seconds) + " seconds.");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            consumer.close();
        }
    }




}
