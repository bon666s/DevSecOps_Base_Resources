package net.gbm.devcenter.billing.utils.openapi;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Invoice Exercise",
                description = "Invoice Exercise",
                version = "1.0.0"
        )
)
public class OpenAPI extends Application {
}
