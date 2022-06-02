package com.moath.ms.sds.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * OpenApi configs.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Profile("prod,dev")
@Configuration
public class OpenApiBeanConfig {

    @Bean
    public OpenAPI customOpenAPI(
        @Value("${open.api.title}") String title,
        @Value("${open.api.version}") String appVersion,
        @Value("${open.api.description}") String description) {

        return new OpenAPI().info(new Info().title(title)
            .version(appVersion)
            .description(description)
            .termsOfService("http://swagger.io/terms/")
            .license(new License().name("Apache 2.0")
                .url("https://springdoc.org")));
    }
}