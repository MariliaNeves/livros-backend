package br.com.livros;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI livrosSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cadastro de Livros")
                        .version("v1")
                        .description("Cadastro de livros, autores, assuntos e relatorio por autor."));
    }

}
