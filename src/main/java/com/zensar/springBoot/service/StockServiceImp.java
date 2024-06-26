package com.zensar.springBoot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.springBoot.dto.StockDto;
import com.zensar.springBoot.entity.StockEntity;
import com.zensar.springBoot.exception.StockNotFoundException;
import com.zensar.springBoot.repo.StockRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Service("SQL")
public class StockServiceImp  implements StockService{
    @Autowired
    StockRepository stockRepository;
    @Autowired
    EntityManager entityManager;
	@Override
	public List<StockDto> getData() {
		// TODO Auto-generated method stub
		List<StockEntity> listOfStocks = stockRepository.findAll();
		List<StockDto> listOfStockDto= new ArrayList();
        for(StockEntity stockEntity : listOfStocks) {
        	StockDto stockDto = new StockDto(stockEntity.getName(),stockEntity.getMarket(),stockEntity.getAmount());
        	 listOfStockDto.add(stockDto);
        }
		return listOfStockDto;
	}

	@Override
	public StockDto addStock(StockDto dto) {
		// TODO Auto-generated method stub
		//stocks.add(dto);
		StockEntity stockEntity = new StockEntity(dto.getName(),dto.getMarket(),dto.getAmount());
		stockRepository.save(stockEntity);
		return dto;
	}

	@Override
	public StockDto updateStock(StockDto dto, int id) {
		// TODO Auto-generated method stub
		/*StockDto dto2 = stocks.stream().filter(stockName-> stockName.getName().equals(name)).findAny().get();
		dto2.setMarket(dto.getMarket());
		dto2.setAmount(dto.getAmount());*/
		Optional<StockEntity> optional = stockRepository.findById(id);
		if(optional.isPresent()) {
			StockEntity stockEntity = optional.get();
			stockEntity.setName(dto.getName());
			stockEntity.setMarket(dto.getMarket());
			stockEntity.setAmount(dto.getAmount());
			stockRepository.save(stockEntity);
			return dto;
		}
		throw new StockNotFoundException(dto.getName());
	}

	@Override
	public StockDto getById(int id) {
		// TODO Auto-generated method stub
		Optional<StockEntity> optional = stockRepository.findById(id);
		if(optional.isPresent()) {
			StockEntity stockEntity = optional.get();
        	StockDto stockDto = new StockDto(stockEntity.getName(),stockEntity.getMarket(),stockEntity.getAmount());
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
		
		Optional<StockEntity> optional = stockRepository.findById(id);
		if(optional.isPresent()) {
			StockEntity stockEntity = optional.get();
			stockRepository.deleteById(id);
        	StockDto stockDto = new StockDto(stockEntity.getName(),stockEntity.getMarket(),stockEntity.getAmount());
        	return stockDto;

		}
        throw new StockNotFoundException(""+id);
	}
	/////////////////////////////////////////////////////

	@Override
	public List<StockDto> findByName(String name) {
		// TODO Auto-generated method stub
		List<StockEntity> findByName = stockRepository.findByName(name);
		
		return convertEntityToDto(findByName);
	}

	

	@Override
	public List<StockDto> findByMarket(String marketname) {
		// TODO Auto-generated method stub
		List<StockEntity> findByMarket = stockRepository.findByMarket(marketname);
		return convertEntityToDto(findByMarket);
	}

	@Override
	public List<StockDto> findByNameAndMarket(String name, String marketname) {
		// TODO Auto-generated method stub
		List<StockEntity> findByNameAndMarket = stockRepository.findByNameAndMarket(name, marketname);
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
		List<StockEntity> findByNameOrderBy =new ArrayList();
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
	private List<StockDto> convertEntityToDto(List<StockEntity> findByMarket) {
		// TODO Auto-generated method stub
		List<StockDto> listOfStockDto = new ArrayList();
		for(StockEntity list : findByMarket) {
			StockDto stockDto =new StockDto(list.getName(),list.getMarket(),list.getAmount());
			listOfStockDto.add(stockDto);
		}
		return listOfStockDto;
	}

	@Override
	public List<StockDto> searchStocksByFilterCriteria(String searchText, String name, String market, String sortedBy,
			int startIndex, int records) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StockEntity> createQuery = criteriaBuilder.createQuery(StockEntity.class);
		Root<StockEntity> from = createQuery.from(StockEntity.class);
		if(name != null && !"".equals(name)) {
			Predicate namePredicate = criteriaBuilder.equal(from.get("name"), name);
			CriteriaQuery<StockEntity> whereName = createQuery.where(namePredicate);
		}
		if(market != null && !"".equals(market)) {
			Predicate marketPredicate = criteriaBuilder.equal(from.get("market"), market);
			CriteriaQuery<StockEntity> whereMarket = createQuery.where(marketPredicate);
		}
		return null;
	}

}
