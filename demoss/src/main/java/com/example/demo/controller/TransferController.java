package com.example.demo.controller;

import com.example.demo.entity.CardInfo;
import com.example.demo.req.Money;
import com.example.demo.respository.CardInfoRepository;
import com.example.demo.service.TransferAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferAccountService transferAccountService;

     @PostMapping(value = "/out")
    public void transferAccounts(@RequestBody @Validated Money money){
         transferAccountService.Dispatch(money);
     }

}
