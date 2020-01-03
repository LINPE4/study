package cn.enjoyedu.sendtype;

import cn.enjoyedu.config.BusiConst;
import cn.enjoyedu.config.KafkaConst;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author King老师   享学课堂 https://enjoy.ke.qq.com
 * 类说明：发送消息--异步模式
 */
public class KafkaAsynProducer {

    private static KafkaProducer<String,String> producer = null;

    public static void main(String[] args) {
        /*消息生产者*/
        producer = new KafkaProducer<String, String>(
                KafkaConst.producerConfig(StringSerializer.class,
                StringSerializer.class));
        /*待发送的消息实例*/
        ProducerRecord<String,String> record;
        try {
            record = new ProducerRecord<String,String>(
                    BusiConst.HELLO_TOPIC,"teacher14","deer");
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata,
                                         Exception exception) {
                    if(null!=exception){
                        exception.printStackTrace();
                        System.out.println("error:" +exception.getMessage());
                    }
                    if(null!=metadata){
                        System.out.println("offset:"+metadata.offset()+"-"
                                +"partition:"+metadata.partition());
                    }
                }
            });
        } finally {
            producer.close();
        }
    }




}
