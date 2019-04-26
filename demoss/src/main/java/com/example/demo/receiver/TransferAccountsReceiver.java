/*
package com.example.demo.receiver;

import com.example.demo.Enum.TransferType;
import com.example.demo.Util.SerialNumber;
import com.example.demo.entity.TransInfos;
import com.example.demo.respositorys.TranInfosRepository;
import com.example.demo.service.TransInfosService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@RabbitListener(queues = "transfer_accounts_dic_queue")
public class TransferAccountsReceiver {
    @Autowired
    private TransInfosService transInfosService;
    @Autowired
    private TranInfosRepository tranInfosRepository;
    // 消息处理器
    @RabbitHandler
    public void process(Message message) {
        System.out.println("Receiver:" + message);
      */
/*//*
/  if (message.getBody()>0){
            TransInfos transInfos = new TransInfos();

            transInfos.setCardNumber("222222222222");
            transInfos.setSerialNumber(SerialNumber.getRandom());
            transInfos.setTransMoney(new BigDecimal(message));
            transInfos.setTransType(TransferType.TOCHANGEINTO.name());
            transInfos.setTransDate(new Date());
            tranInfosRepository.save(transInfos);*//*

        //}
    }
}
*/
