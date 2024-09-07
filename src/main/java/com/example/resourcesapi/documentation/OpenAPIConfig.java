package com.example.resourcesapi.documentation;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Community Center API")
                        .version("1.0")
                        .description("API for CommunityCenter")
                        .contact(new Contact()
                                .name("Gerson Vieira")
                                .email("gerson.pires@dcx.ufpb.br")
                                .url("https://github.com/GersonVieira"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}