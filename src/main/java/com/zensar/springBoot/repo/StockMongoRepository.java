package com.zensar.springBoot.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.springBoot.entity.StockDocument;
import com.zensar.springBoot.entity.StockEntity;

public interface StockMongoRepository extends MongoRepository<StockDocument, Integer> {

	public List<StockDocument> findByMarket(String market);
	public List<StockDocument> findByName(String name);
	public List<StockDocument> findByNameAndMarket(String name,String market);
	//public List<StockEntity> findByNameIsContians(String name);
	//public List<StockEntity> findByNameLike(String name);
	//Sorting
	public List<StockDocument> findByOrderByNameAsc();
	public List<StockDocument> findByOrderByNameDesc();
	
}
