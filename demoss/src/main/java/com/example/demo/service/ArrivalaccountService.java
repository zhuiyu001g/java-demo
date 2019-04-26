/*
package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Enum.TransferType;
import com.example.demo.Util.SerialNumber;
import com.example.demo.entity.CardInfo;
import com.example.demo.entity.CardInfos;
import com.example.demo.entity.TransInfos;
import com.example.demo.respositorys.CardInfosRepository;
import com.example.demo.respositorys.TranInfosRepository;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Service
public class ArrivalaccountService {
    @Autowired
    private TranInfosRepository tranInfosRepository;
    @Autowired
    private CardInfosRepository cardInfosRepository;
    @Transactional(rollbackFor = Throwable.class)
    @RabbitListener(queues = "transfer_accounts_dic_queues")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");

        System.out.println("到账服务平台" + msg + ",消息id:" + messageId);
        */
/*JSONObject jsonObject = JSONObject.parseObject(msg);
        String msgs = jsonObject.getString("msg");*//*

        System.out.println(msg);
        System.out.println("消息"+msg);
        String money = msg.substring(0,msg.indexOf("."));
        String cardNumbers = msg.substring(msg.indexOf(".")+
                1);
        if (StringUtils.isEmpty(msg)) {
            // 日志记录
            return;
        }
        CardInfos cardInfos = cardInfosRepository.findByCardNumber(cardNumbers);
        TransInfos transInfos = new TransInfos();

        transInfos.setCardInfos(cardInfos);
        transInfos.setSerialNumber(SerialNumber.getRandom());
        transInfos.setTransMoney(new BigDecimal(Long.valueOf(money)));
        transInfos.setTransType(TransferType.TURNOUT.name());
        transInfos.setTransDate(new Date());
        try {
            TransInfos transInfos1 = tranInfosRepository.save(transInfos);
            if (transInfos1 !=null) {
                // 手动签收消息,通知mq服务器端删除该消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // // 丢弃该消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
*/
