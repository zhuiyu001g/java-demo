package com.example.demo.service;

import com.example.demo.Enum.TransferType;
import com.example.demo.entity.TransInfo;

import com.example.demo.entity.TransInfos;
import com.example.demo.respositorys.TranInfosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransInfosService {
    @Autowired
    private TranInfosRepository tranInfosRepository;
    @Transactional(rollbackFor = Exception.class)
    public TransInfos tochangeInto(Long money){
        BigDecimal moneys = new BigDecimal(money);
        TransInfos transInfos = new TransInfos();
        transInfos.setTransDate(new Date());
        transInfos.setTransMoney(moneys);
        transInfos.setTransType(TransferType.TOCHANGEINTO.name());

        return tranInfosRepository.save(transInfos);
    }
}
