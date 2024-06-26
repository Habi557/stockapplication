package com.zensar.springBoot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.springBoot.Application;
import com.zensar.springBoot.dto.StockDto;
import com.zensar.springBoot.entity.StockDocument;
import com.zensar.springBoot.entity.StockEntity;
import com.zensar.springBoot.exception.StockNotFoundException;
import com.zensar.springBoot.repo.StockMongoRepository;
import com.zensar.springBoot.repo.StockRepository;
@Service("Mongo_Service")
public class StockMongoServiceImpl implements StockService {

	 @Autowired
	 StockMongoRepository stockRepository;
	 @Autowired
	 StockRepository stockReposMysql;
	 @Autowired
	 Application application;
		@Override
		public List<StockDto> getData() {
			// TODO Auto-generated method stub
			/*List<StockEntity> listOfStocks = stockRepository.findAll();
			List<StockDto> listOfStockDto= new ArrayList();
	        for(StockDocument stockEntity : listOfStocks) {
	        	StockDto stockDto = new StockDto(stockEntity.getName(),stockEntity.getMarket(),stockEntity.getAmount());
	        	
	        	listOfStockDto.add(stockDto);
	        }*/
			List<StockEntity> listOfStocks = stockReposMysql.findAll();
			List<StockDto> listOfStockDto= new ArrayList();
	        for(StockEntity stockEntity : listOfStocks) {
	        	//StockDto stockDto = new StockDto(stockEntity.getName(),stockEntity.getMarket(),stockEntity.getAmount());
	        	ModelMapper modelMapper = application.getModelMapper();
	        	StockDto map = modelMapper.map(listOfStocks,  StockDto.class);
	        	listOfStockDto.add(map);
	        }
			
			return listOfStockDto;
		}

		@Override
		public StockDto addStock(StockDto dto) {
			// TODO Auto-generated method stub
			//stocks.add(dto);
			//StockDocument stockDocument = new StockDocument(dto.getId(),dto.getName(),dto.getMarket(),dto.getAmount());
			//stockRepository.save(stockDocument);
			ModelMapper modelMapper = application.getModelMapper();
        	StockEntity stockEntity = modelMapper.map(dto,  StockEntity.class);
        	stockReposMysql.save(stockEntity);
			return dto;
		}

		@Override
		public StockDto updateStock(StockDto dto, int id) {
			// TODO Auto-generated method stub
			/*StockDto dto2 = stocks.stream().filter(stockName-> stockName.getName().equals(name)).findAny().get();
			dto2.setMarket(dto.getMarket());
			dto2.setAmount(dto.getAmount());*/
			Optional<StockDocument> optional = stockRepository.findById(id);
			if(optional.isPresent()) {
				StockDocument stockDocument = optional.get();
				stockDocument.setName(dto.getName());
				stockDocument.setMarket(dto.getMarket());
				stockDocument.setAmount(dto.getAmount());
				stockRepository.save(stockDocument);
				return dto;
			}
			throw new StockNotFoundException(dto.getName());
		}

		@Override
		public StockDto getById(int id) {
			// TODO Auto-generated method stub
			Optional<StockDocument> optional = stockRepository.findById(id);
			if(optional.isPresent()) {
				StockDocument stockDocument = optional.get();
	        	StockDto stockDto = new StockDto(stockDocument.getName(),stockDocument.getMarket(),stockDocument.getAmount());
	        	return stockDto;

			}
			else {
		           throw new StockNotFoundException(""+id);

			}
			/*try {
				StockDto dto2 = stocks.stream().filter(stock-> stock.getName().equals(stockName)).findAny().get();
	             return dto2;
			}catch(Exception e) {
		           throw new StockNotFoundException(stockName);

			}*/
			
	}
		
		
		/*public static List<StockDto> stocks = new ArrayList();
		static {
			stocks.add(new StockDto("IBM","NSE company",500));
			stocks.add(new StockDto("TCS","BSE company",500));

		}*/
		@Override
		public StockDto deleteById(int id) {
			// TODO Auto-generated method stub
			
			Optional<StockDocument> optional = stockRepository.findById(id);
			if(optional.isPresent()) {
				StockDocument stockDocument = optional.get();
				stockRepository.deleteById(id);
	        	StockDto stockDto = new StockDto(stockDocument.getName(),stockDocument.getMarket(),stockDocument.getAmount());
	        	return stockDto;

			}
	        throw new StockNotFoundException(""+id);
		}
		/////////////////////////////////////////////////////

		@Override
		public List<StockDto> findByName(String name) {
			// TODO Auto-generated method stub
			List<StockDocument> findByName = stockRepository.findByName(name);
			
			return convertEntityToDto(findByName);
		}

		

		@Override
		public List<StockDto> findByMarket(String marketname) {
			// TODO Auto-generated method stub
			List<StockDocument> findByMarket = stockRepository.findByMarket(marketname);
			return convertEntityToDto(findByMarket);
		}

		@Override
		public List<StockDto> findByNameAndMarket(String name, String marketname) {
			// TODO Auto-generated method stub
			List<StockDocument> findByNameAndMarket = stockRepository.findByNameAndMarket(name, marketname);
			return convertEntityToDto(findByNameAndMarket);
		}

		@Override
		public List<StockDto> findByNameLike(String name) {
			// TODO Auto-generated method stub
			//List<StockEntity> findByNameLike = stockRepository.findByNameLike(name);
			return null;
		}

		@Override
		public List<StockDto> findByOrderByName(String sortType) {
			// TODO Auto-generated method stub
			List<StockDocument> findByNameOrderBy =new ArrayList();
			if(sortType.equals("ASC")) {
				 findByNameOrderBy = stockRepository.findByOrderByNameAsc();
			}else if(sortType.equals("ASC")) {
				 findByNameOrderBy = stockRepository.findByOrderByNameDesc();

			}
			return convertEntityToDto(findByNameOrderBy);
		}

		@Override
		public List<StockDto> findByPage(int startIndex, int records) {
			// TODO Auto-generated method stub
			return null;
		}
		private List<StockDto> convertEntityToDto(List<StockDocument> findByMarket) {
			// TODO Auto-generated method stub
			List<StockDto> listOfStockDto = new ArrayList();
			for(StockDocument list : findByMarket) {
				StockDto stockDto =new StockDto(list.getName(),list.getMarket(),list.getAmount());
				listOfStockDto.add(stockDto);
			}
			return listOfStockDto;
		}

		@Override
		public List<StockDto> searchStocksByFilterCriteria(String searchText, String name, String market,
				String sortedBy, int startIndex, int records) {
			// TODO Auto-generated method stub
			return null;
		}

}
