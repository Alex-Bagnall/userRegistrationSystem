package com.bagnall.urs.repository;

import org.springframework.stereotype.Repository;

import com.bagnall.urs.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	
	User findByCountry(String country);
}
