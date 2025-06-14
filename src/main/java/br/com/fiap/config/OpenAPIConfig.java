package br.com.fiap.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("InvestBot - API de Usuários")
                        .description("API REST para cadastro e gerenciamento de usuários do InvestBot")
                        .version("1.0.0"));
    }
}

