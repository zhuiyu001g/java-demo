package com.example.demo.respository;

import com.example.demo.entity.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardInfoRepository extends JpaRepository<CardInfo,Integer>, JpaSpecificationExecutor<CardInfo> {
    CardInfo findByCardNumber(String cardNumber);
}
