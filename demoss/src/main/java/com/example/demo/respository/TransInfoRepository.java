package com.example.demo.respository;

import com.example.demo.entity.TransInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransInfoRepository extends JpaRepository<TransInfo,Integer>, JpaSpecificationExecutor<TransInfo> {
}
