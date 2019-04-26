package com.example.demo.transaction;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.example.demo.service.TransInfosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class Transaction {

    @Autowired
    private TransInfosService transInfosService;
    public void sendMessageInTransactions(Long money) throws MQClientException {
        //事务回查监听器
       TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        //事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("groupName");
        //MQ服务器地址
        producer.setNamesrvAddr("192.168.56.105:9876;192.168.106:9876");
        //注册事务回查监听
        producer.setTransactionCheckListener(transactionCheckListener);
        //本地事务执行器
        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
        //启动生产者
        producer.start();

        Message msg = new Message("TransactionTopic", "tag", "KEY1", "hello RocketMQ 1".getBytes());

        SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, money);
        log.info(new Date()+"sendResult={}",sendResult.toString());
        producer.shutdown();

    }

    public void MQClient(Long money) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("groupName");
        consumer.setNamesrvAddr("192.168.56.105:9876;192.168.56.106:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //消费事务消息
        consumer.subscribe("TransactionTopic","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt ext:msgs) {
                    try {
                        System.out.println(new Date() + new String(ext.getBody(),"UTF-8"));
                        if (ext.getTopic().equals("TransactionTopic")){
                            transInfosService.tochangeInto(money);
                        }
                    } catch (UnsupportedEncodingException e) {
                       e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Start............");
    }

}
