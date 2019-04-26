package com.example.demo.respositorys;

import com.example.demo.entity.CardInfos;
import com.example.demo.entity.TransInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TranInfosRepository extends JpaRepository<TransInfos,Integer>, JpaSpecificationExecutor<TransInfos> {

}
