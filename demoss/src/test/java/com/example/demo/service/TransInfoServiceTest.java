package com.example.demo.service;

import com.example.demo.respository.UserInfoRepository;
import com.example.demo.respositorys.CardInfosRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransInfoServiceTest {

    @Autowired
    private TransInfoService transInfoService;
    @Autowired
    private CardInfosRepository cardInfosRepository;
    @Test
    public void findUserInfo() {
        System.out.println(transInfoService.findUserInfo());
    }
    @Test
    public void find(){
        System.out.println(cardInfosRepository.findByCardNumber("222222222222"));
    }
}