package com.spocket.assignment.searchapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/spocket/api/v1/products/*"))
                .apis(RequestHandlerSelectors.basePackage("com.spocket.assignment.searchapi"))
                .build().apiInfo(apiDetails());

    }
    
    @SuppressWarnings("deprecation")
	private ApiInfo apiDetails() {
    	return new ApiInfo("Spocket Search API",
    			"Spocket products search API details",
    			"1.0", 
    			"", 
    			"junaidalam.2000@gmail.com", "", "");
    }
}