package com.zensar.springBoot.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.zensar.springBoot.dto.StockDto;

public interface StockService {
	public List<StockDto> getData();
	public StockDto addStock(StockDto dto);
	public StockDto updateStock(StockDto dto, int id) ;
	public StockDto getById(int  id);
	public StockDto deleteById(int id);
	//////////////////
	public List<StockDto> findByName(String name);
	public List<StockDto> findByMarket(String marketname);
	public List<StockDto> findByNameAndMarket(String name, String marketname);
	public List<StockDto> findByNameLike(String name);
	public List<StockDto> findByOrderByName(String sortType);
	public List<StockDto> findByPage(int startIndex, int records);
	public List<StockDto> searchStocksByFilterCriteria(String searchText, String name, String market, String sortedBy,
			int startIndex, int records);
	




}
