package com.peter.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

public class ElasticSearchClientClusterTest {

    private TransportClient client;

    @Before
    public void init() throws Exception {
        //创建一个Settings对象
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .build();
        //创建一个TransPortClient对象
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9302))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9303));

    }

    @Test
    public void createIndex() throws Exception {
        //1、创建一个Settings对象，相当于是一个配置信息。主要配置集群的名称。
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .build();
        //2、创建一个客户端Client对象
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9302));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9303));
        //3、使用client对象创建一个索引库
        client.admin().indices().prepareCreate("index_hello")
                //执行操作
                .get();
        //4、关闭client对象
        client.close();
    }

    @Test
    public void setMappings() throws Exception {
        //创建一个Settings对象
        Settings settings = Settings.builder()
                .put("cluster.name", "my-elasticsearch")
                .build();
        //创建一个TransPortClient对象
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9302))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9303));
        //创建一个Mappings信息
        /*{
            "article":{
            "properties":{
                "id":{
                    "type":"long",
                            "store":true
                },
                "title":{
                    "type":"text",
                            "store":true,
                            "index":true,
                            "analyzer":"ik_smart"
                },
                "content":{
                    "type":"text",
                            "store":true,
                            "index":true,
                            "analyzer":"ik_smart"
                }
            }
        }
        }*/
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .startObject("article")
                        .startObject("properties")
                            .startObject("id")
                                .field("type", "long")
                                .field("store", true)
                            .endObject()
                            .startObject("title")
                                .field("type", "text")
                                .field("store", true)
                                .field("analyzer", "ik_smart")
                            .endObject()
                            .startObject("content")
                                .field("type", "text")
                                .field("store", true)
                                .field("analyzer", "ik_smart")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
        //使用client把mapping信息设置到索引库中
        client.admin().indices()
                //设置要做映射的索引
                .preparePutMapping("index_hello")
                //设置要做映射的type
                .setType("article")
                //mapping信息，可以是XContentBuilder对象可以是json格式的字符串
                .setSource(builder)
                //执行操作
                .get();
        //关闭链接
        client.close();
    }


}