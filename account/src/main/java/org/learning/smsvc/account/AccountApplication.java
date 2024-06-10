package org.learning.smsvc.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition (
		info = @Info(
				title = "Accounts microservice REST API documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact (
						name = "Shubham Singh",
						email = "tempmail@mail.com",
						url = "https://www.iife.me"
				),
				license = @License (
						name = "Apache 2.0",
						url="https://www.iife.me"
				)
		),
		externalDocs = @ExternalDocumentation (
				description = "EazyBank Accounts microservice REST API Documentation",
				url = "https://iife.me"
		)

)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
