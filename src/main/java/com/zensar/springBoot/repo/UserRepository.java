package com.zensar.springBoot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.springBoot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public List<UserEntity> findByUserName(String name);
}
