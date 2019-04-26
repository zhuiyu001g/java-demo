package com.example.demo.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;
import com.example.demo.service.TransInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TransactionExecuterImpl implements LocalTransactionExecuter {


    @Autowired
    private TransInfoService transInfoService;

    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
        try {
            transInfoService.turnOut((Long) o);
            System.out.println(new Date()+"本地事务执行成功，确认发送消息");
        }catch (Exception e){
            System.out.println(new Date()+"本地事务执行失败");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
