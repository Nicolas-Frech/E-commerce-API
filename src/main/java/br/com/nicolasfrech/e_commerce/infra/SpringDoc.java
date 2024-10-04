package br.com.nicolasfrech.e_commerce.infra;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDoc {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API - API REST para um E-commerce com funcionalidades de registro, listagem, atualização, exclusão de produtos e mais!")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("nicolasfrechdev1@gmail.com")));
    }


}
