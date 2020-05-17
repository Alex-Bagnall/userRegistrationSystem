package com.bagnall.urs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bagnall.urs.model.UserInfo;

@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long>{
	public UserInfo findByName(String username);
}
