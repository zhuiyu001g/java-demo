package com.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "TransInfo")
@Table(name = "trans_info")
@Component
public class TransInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trans_date")
    private Date transDate;
    @ManyToOne
    @JoinColumn(name = "card_number",referencedColumnName = "card_number")
    private CardInfo cardInfo;
    @Column(name = "trans_type")
    private String transType;
    @Column(name = "trans_money")
    private BigDecimal transMoney;
    @Column(name = "remark")
    private String remark;
    @Column(name = "serial_number")
    private String serialNumber;
}
