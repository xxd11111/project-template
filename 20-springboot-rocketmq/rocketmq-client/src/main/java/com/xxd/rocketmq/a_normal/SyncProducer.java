package com.xxd.rocketmq.a_normal;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import javax.swing.text.html.HTML;

/**
 * 同步消息发送生产者
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建一个producer，参数为Producer Group名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        // 指定nameServer地址
        producer.setNamesrvAddr("192.168.193.30:9876");
        // 设置当发送失败时重试发送的次数，默认为2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限为5s，默认3s
        producer.setSendMsgTimeout(5000);

        // 开启生产者
        producer.start();

        for (int i = 0; i< 100; i++){
            byte[] body = ("Hello,"+i).getBytes();
            //为消息指定key
            Message msg = new Message("topic1", "tag1", body);
            //发送消息
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
