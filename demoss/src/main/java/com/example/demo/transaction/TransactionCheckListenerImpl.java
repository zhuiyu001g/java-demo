package com.example.demo.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.Date;

/**
 * 事务回查逻辑
 *
 *  未决事务，服务器端回查客户端
 *
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
        System.out.println("server checking TrMsg"+messageExt.toString());
        //由于RocketMQ迟迟没有收到消息的确认消息，因此主动询问这条prepare消息，是否正常？
        //        //可以查询数据库看这条数据是否已经处理
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
