package com.cg.bookstore;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
public class BookStoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreManagementApplication.class, args);
	}
	@Bean
	public Docket createRestApi(){
	  return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiDetails())
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.cg.bookstore"))
	      .paths(PathSelectors.any())
	      .build();
	}
	
	private ApiInfo apiDetails() {
	    return new ApiInfo(
	      "BOOK STORE MANAGEMENT API", 
	      "Team members->Ankita Basu,"
	      + "Anupam Roy,"
	      + "Shounak Sengupta,"
	      + "Roshma Ritanjit,"
	      + "Akash Pal,"
	      + "Riya Atta", 
	      "API TOS", 
	      "Terms of service", 
	      new springfox.documentation.service.Contact("", "http://localhost:9099/swagger-ui/", "ankitabasu0608@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}

}
