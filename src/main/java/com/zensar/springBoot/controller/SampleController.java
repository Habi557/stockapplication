package com.zensar.springBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SampleController {
 
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@GetMapping("/stock/db/url")
	public String getDbUrl() {
		return this.dbUrl;
	}
}
