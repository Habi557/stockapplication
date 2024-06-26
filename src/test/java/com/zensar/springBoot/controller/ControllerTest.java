package com.zensar.springBoot.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.springBoot.dto.StockDto;
import com.zensar.springBoot.service.StockService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest(Controller.class)
public class ControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	StockService stockService;
	@Autowired
	ObjectMapper objectMapper;
	@Test
	public void testGetAllStocks() throws Exception {
		List<StockDto> stocks = new ArrayList();
		stocks.add(new StockDto("Tcs","BSE",5000));
		stocks.add(new StockDto("IBM","NSE",6000));
		stocks.add(new StockDto("ZENSAR","BSE",4000));
		when(stockService.getData()).thenReturn(stocks);

		MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:9000/zensar/"))
		.andExpect(status().isOk())
		.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		assertTrue(response.contains("IBM"));	
	}
	
	//@Test
//	public void testCreateStock() throws Exception {
//		StockDto stockDto = new StockDto("Tcs","BSE",5000);
//		when(stockService.addStock(stockDto)).thenReturn(stockDto);
//
//		MvcResult mvcResult= this.mockMvc.perform(post("http://localhost:9000/zensar/create"))
//		.content(objectMapper.writeValueAsString(stockDto))
//		.andExpect(status().isCreated())
//		.andReturn();
//		
//		String response = mvcResult.getResponse().getContentAsString();
//		assertTrue(response.contains("IBM"));
//	}
	//@Test
//	public void testCreateStockByBlankName() throws Exception {
//		StockDto stockDto = new StockDto("","BSE",5000);
//		when(stockService.addStock(stockDto)).thenReturn(stockDto);
//
//		MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:9000/zensar/create")).contentType("application/json")
//		.content(objectMapper.writeValueAsString(stockDto))
//		.andExpect(status().isBadRequest())
//		.andReturn();
//		
//		String response = mvcResult.getResponse().getContentAsString();
//		assertTrue(response.contains("Invalid request content"));
//	}
	

	//@Test
	public void  testUpdateStock() throws Exception {
		StockDto stockDto = new StockDto("","BSE",5000);
		when(stockService.updateStock(stockDto, 3)).thenReturn(stockDto);
		
		//MvcResult mvcResult=this.mockMvc.perform(put("http://localhost:9000/zensar/update/3").contentType("application/json")
				//.andExpect(status().isOk())
				//.andReturn();

	}
}
