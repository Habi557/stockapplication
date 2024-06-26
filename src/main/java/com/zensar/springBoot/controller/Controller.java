package com.zensar.springBoot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.springBoot.dto.StockDto;
import com.zensar.springBoot.service.StockService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/zensar")
public class Controller {
	@Autowired
	@Qualifier("SQL")
	StockService stockService;
	@Parameter(name="Dto object")
	@GetMapping("/")
	public ResponseEntity<List<StockDto>> getData(){
		return new ResponseEntity<List<StockDto>>(stockService.getData(),HttpStatus.OK);
	}
	@Parameters()
	@PostMapping(value="/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<StockDto> addStock(@Valid @RequestBody StockDto dto) {
		return new ResponseEntity<StockDto>(stockService.addStock(dto), HttpStatus.CREATED);
	}
	@PutMapping(value="/update/{stokId}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<StockDto> updateStock(@RequestBody StockDto dto, @PathVariable("stokId") int id) {
		
		return new ResponseEntity<StockDto>(stockService.updateStock(dto, id), HttpStatus.OK);
	}
	
	@GetMapping("/byId/{stockId}")
	public ResponseEntity<StockDto> getById(@PathVariable("stockId") int  id){
	
		return new ResponseEntity<StockDto>(stockService.getById(id),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{stockId}")
	public ResponseEntity<StockDto> deleteById(@PathVariable("stockId") int id){
		return new ResponseEntity<StockDto>(stockService.deleteById(id),HttpStatus.OK); 
	}
///////////////////////////////////////////////////////////
			@GetMapping(value="/stock/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)

		public ResponseEntity<List<StockDto>> getStocksByName(@PathVariable("name") String name) {

			return new ResponseEntity<List<StockDto>>(stockService.findByName(name), HttpStatus.OK);

		}
	 
		@GetMapping(value="/stock/marketname/{marketname}", produces = MediaType.APPLICATION_JSON_VALUE)

		public List<StockDto> getStocksByMarketName(@PathVariable("marketname") String marketname) {

			return stockService.findByMarket(marketname);

		}
	 
		@GetMapping(value="/stock/marketname/{marketname}/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)

		public List<StockDto> getStocksByNameAndMarketName(@PathVariable("marketname") String marketname, @PathVariable("name") String name) {

			return stockService.findByNameAndMarket(name, marketname);

		}
	 
		@GetMapping(value="/stock/name/like/{name}", produces = MediaType.APPLICATION_JSON_VALUE)

		public List<StockDto> getStocksByNameLike(@PathVariable("name") String name) {

			return stockService.findByNameLike(name);

		}
	 
		@GetMapping(value="/stock/name/sort/{sortType}", produces = MediaType.APPLICATION_JSON_VALUE)

		public List<StockDto> getStocksOrderByName(@PathVariable("sortType") String sortType) {

			return stockService.findByOrderByName(sortType);

		}
	 
		@GetMapping(value="/stock/page/{startIndex}/{records}", produces = MediaType.APPLICATION_JSON_VALUE)

		public List<StockDto> getStocksByPage(@PathVariable("startIndex") int startIndex, @PathVariable("records") int records) {

			return stockService.findByPage(startIndex, records);

		}
		@GetMapping(value="/stock/search/filtercriteria", produces=MediaType.APPLICATION_JSON_VALUE)
		public List<StockDto> searchStocksByFilterCriteria(@RequestParam(name="searchText", required = false)String searchText,
				@RequestParam(name = "name", required = false)String name, @RequestParam(name="market", required = false)String market,
				@RequestParam(name="sortedBy", required = false)String sortedBy,
				@RequestParam(name = "startIndex", defaultValue="0", required = false)int startIndex,
				@RequestParam(name="records", defaultValue = "10", required = false)int records
				) {
			return stockService.searchStocksByFilterCriteria(searchText, name, market, sortedBy, startIndex, records);
		}
	 


}
