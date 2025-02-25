package br.com.fiap.api_rest_aula01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "API de Clientes", description = "Exemplo de API RESTful com Swagger", version = "v1"))
public class ApiRestAula01Application {


		public static void main(String[] args) {
			SpringApplication.run(ApiRestAula01Application.class, args);
		}

	}