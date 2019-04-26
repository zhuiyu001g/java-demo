package com.example.demo.respositorys;

import com.example.demo.entity.CardInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardInfosRepository extends JpaRepository<CardInfos,Integer>, JpaSpecificationExecutor<CardInfos> {
    CardInfos findByCardNumber(String cardNumber);
}
