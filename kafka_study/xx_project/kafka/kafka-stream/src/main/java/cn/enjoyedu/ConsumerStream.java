package cn.enjoyedu;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 需要往期视频的同学加QQ：2070155495（七七老师）
 * 需要咨询VIP课程的同学加QQ：1399484076（芊芊老师
 * 类说明：
 */
public class ConsumerStream {

    public static void main(String[] args) throws Exception{

        Properties props = new Properties();
        /*每个stream应用都必须有唯一的id*/
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "phone_count");
        /*kafka的地址*/
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        /*消息的序列化机制*/
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        /*每次获取数据从头开始*/
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        /*流的建造器*/
        KStreamBuilder builder = new KStreamBuilder();
        /*流的输入源，kafka中主题phone*/
        KStream<String, String> source = builder.stream("phone");

        /*正则负责拆分文本为单词*/
        final Pattern pattern = Pattern.compile("\\W+");
        KStream counts  =
                source.flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
                .map((key, value) -> new KeyValue<Object, Object>(value, value))
                .filter((key, value) -> (value.equals("huawei")))
                .groupByKey()
                .count("sell")
                .mapValues(value->Long.toString(value)).toStream();
        //打印出来
        //counts.to("output");//通过to方法的将结果写入Kafka的主题 output中
        counts.print();
        //kafka中流处理 异步的执行(类似于mysql中的存储过程)
        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.cleanUp();
        streams.start();
    }
}
