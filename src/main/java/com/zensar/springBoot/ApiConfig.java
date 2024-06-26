package com.zensar.springBoot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Stock Management REST API Documentation",
				description = "Stock Management application",
				version = "1.1",
				license = @License(
						name = "LGPL",
						url = "http://lgpl.com"
				),
				contact = @Contact(
						name = "Anand",
						email = "anand.kulkarni1@zensar.com"
				)
		)		
)
public class ApiConfig {

}
