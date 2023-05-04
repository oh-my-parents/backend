package omp.omp.global.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Backend", version = "v1"))
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi SecurityGroupOpenApi() {
        return GroupedOpenApi
                .builder()
                .group("Security Open Api")
                //.pathsToExclude("/auth/*", "/")
                .addOpenApiCustomiser(buildSecurityOpenApi())
                .build();
    }

//    @Bean
//    public GroupedOpenApi NonSecurityGroupOpenApi(@Value("${spring.profiles.active}") String active) {
//        return GroupedOpenApi
//                .builder()
//                .group("Non Security Open Api")
//                .pathsToMatch("/auth/*")
//                .build();
//    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .bearerFormat("JWT")
                .scheme("bearer");

        return OpenApi -> OpenApi
                .addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .getComponents().addSecuritySchemes("jwt token", securityScheme);
    }

}
