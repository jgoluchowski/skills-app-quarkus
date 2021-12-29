package org.altimetrik.skills.configuration;

import static org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType.HTTP;

import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title = "Skills App",
        version = "1.0.0"
    ),
    components = @Components(
        securitySchemes = {
            @SecurityScheme(
                securitySchemeName = "bearerAuth",
                type = HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
            )
        }
    ),
    security = {
        @SecurityRequirement(name = "bearerAuth")
    }
)
public class SwaggerConfiguration extends Application {

}
