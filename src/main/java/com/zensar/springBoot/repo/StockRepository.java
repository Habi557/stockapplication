package com.zensar.springBoot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.springBoot.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer> {
	
	public List<StockEntity> findByMarket(String market);
	public List<StockEntity> findByName(String name);
	public List<StockEntity> findByNameAndMarket(String name,String market);
	//public List<StockEntity> findByNameIsContians(String name);
	//public List<StockEntity> findByNameLike(String name);
	//Sorting
	public List<StockEntity> findByOrderByNameAsc();
	public List<StockEntity> findByOrderByNameDesc();




}
