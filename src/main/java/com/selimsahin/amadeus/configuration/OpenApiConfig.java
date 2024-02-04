package com.selimsahin.amadeus.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Selim Sahin",
                        email = "selimshin.sns@gmail.com",
                        url = "https://github.com/selimsahindev"
                ),
                description = "This is a simple REST API for Amadeus.",
                title = "Amadeus Flights API - Selim Sahin",
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
        },
        security = {
                @SecurityRequirement(name = "basicAuth")
        }
)
@SecurityScheme(
        name = "basicAuth",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
