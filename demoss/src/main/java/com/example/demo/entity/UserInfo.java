package com.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "UserInfo")
@Table(name = "user_info")
@Component
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "person_id")
    private String personId;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "address")
    private String address;
}
