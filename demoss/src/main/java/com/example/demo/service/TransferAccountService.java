package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Enum.TransferType;
import com.example.demo.Util.SerialNumber;
import com.example.demo.base.BaseApiService;
import com.example.demo.base.ResponseBase;
import com.example.demo.entity.CardInfo;
import com.example.demo.entity.TransInfo;
import com.example.demo.req.Money;
import com.example.demo.respository.CardInfoRepository;
import com.example.demo.respository.TransInfoRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransferAccountService extends BaseApiService implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TransInfoRepository transInfoRepository;
    @Autowired
    private CardInfoRepository cardInfoRepository;
    @Transactional(rollbackFor = Throwable.class)
    public ResponseBase Dispatch(Money money) {
        //先下单 订单表插入数据
        CardInfo cardInfo = cardInfoRepository.findByCardNumber(money.getCardNumber());
        BigDecimal moneys = new BigDecimal(money.getMoney());
        TransInfo transInfo = new TransInfo();
        transInfo.setTransDate(new Date());
        transInfo.setTransMoney(moneys);
        transInfo.setTransType(TransferType.TOCHANGEINTO.name());
        transInfo.setSerialNumber( SerialNumber.getRandom());
        transInfo.setCardInfo(cardInfo);
       TransInfo transInfo1= transInfoRepository.save(transInfo);
        if (transInfo1 ==null) {
            return setResultError("下单失败!");
        }

        String msg = String.valueOf(money.getMoney())+"."+money.getCardNumbers();
        // 2.订单表插插入完数据后 订单表发送 外卖小哥
        send(msg);
        //    int i = 1/0;   //发生异常
        return setResultSuccess();
    }

    private void send(String msg) {
        JSONObject jsonObect = new JSONObject();
        jsonObect.put("msg", msg);
        String msgs = jsonObect.toJSONString();
        System.out.println("msgs:" + msgs);
        System.out.println(msg);
        // 封装消息
        Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(msg).build();
        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(msg);
        // 发送消息
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);

        rabbitTemplate.convertAndSend("transfer_accounts_exchange_names", "TransferAccountsRoutingKeys", message, correlationData);

    }
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = correlationData.getId();
        System.out.println("消息id:" + correlationData.getId());
        if (ack) { //消息发送成功
            System.out.println("消息发送确认成功");
        } else {
            //重试机制
            send(msgId);
            System.out.println("消息发送确认失败:" + cause);
        }

    }
//如果消息没有到exchange,则confirm回调,ack=false
//
//如果消息到达exchange,则confirm回调,ack=true
//
//exchange到queue成功,则不回调return
//
//******exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message);
    }
}
