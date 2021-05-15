package com.tayyab.employeecrud.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	// http://localhost:8080/tayyab-springboot/swagger-ui/
	
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.any())
	      .paths(PathSelectors.any())
	      .build()
	      .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Employee CRUD", 
	      "Employee CRUD Spring Boot Project", 
	      "Employee TOS", 
	      "http://localhost:8080/tayyab-springboot/v2/api-docs", 
	      new Contact("Tayyab khan", "https://www.linkedin.com/in/tayyab-khan-pathan-458902105/", "khantayyab702@gmail.com"), 
	      "Source Code URL", "https://www.github.com/Tayyabkhan143/employee-crud", Collections.emptyList());
	}

}
