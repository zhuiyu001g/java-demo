package com.example.demo.service;

import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.example.demo.Enum.TransferType;
import com.example.demo.Util.SerialNumber;
import com.example.demo.entity.TransInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.respository.CardInfoRepository;
import com.example.demo.respository.TransInfoRepository;
import com.example.demo.respository.UserInfoRepository;
import com.example.demo.transaction.TransactionCheckListenerImpl;
import com.example.demo.transaction.TransactionExecuterImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransInfoService {
    @Autowired
    private TransInfoRepository transInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
   @Transactional(rollbackFor = Throwable.class)
    public TransInfo turnOut(Long money){
        BigDecimal moneys = new BigDecimal(money);
        TransInfo transInfo = new TransInfo();
        transInfo.setTransDate(new Date());
        transInfo.setTransMoney(moneys);
        transInfo.setTransType(TransferType.TOCHANGEINTO.name());
        transInfo.setSerialNumber( SerialNumber.getRandom());

        return transInfoRepository.save(transInfo);
    }
    public List<TransInfo> findUserInfo(){
        return transInfoRepository.findAll();
    }
}
