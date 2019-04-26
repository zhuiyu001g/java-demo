package com.example.demo.respositorys;

import com.example.demo.entity.UserInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfosRepository extends JpaRepository<UserInfos,Integer>, JpaSpecificationExecutor<UserInfos> {
}
