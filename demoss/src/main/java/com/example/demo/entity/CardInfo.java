package com.example.demo.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "CardInfo")
@Table(name = "card_info")
@Component
public class CardInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "card_number")
    private String cardNumber ;
    @Column(name = "open_money")
    private String openMoney;
    @Column(name = "balance")
    private String balance;
    @Column(name = "password")
    private String password;
    @Column(name = "user_info_id")
    private Integer ser_info_id;
    @Column(name = "islock")
    private Integer islock;
}
