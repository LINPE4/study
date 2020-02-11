package cn.enjoyedu.producer;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RocketMQProducer {
    private static final Logger logger = LoggerFactory.getLogger(RocketMQProducer.class);

    private DefaultMQProducer defaultMQProducer;
    private String producerGroup;
    private String namesrvAddr;

    public void init() throws MQClientException {
        this.defaultMQProducer = new DefaultMQProducer(this.producerGroup);
        defaultMQProducer.setNamesrvAddr(this.namesrvAddr);
        defaultMQProducer.start();
        logger.info("rocketMQ初始化生产者完成[producerGroup：" + producerGroup + "]");
    }

    public void destroy() {
        defaultMQProducer.shutdown();
        logger.info("rocketMQ生产者[producerGroup: " + producerGroup + "]已停止");
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }



}
