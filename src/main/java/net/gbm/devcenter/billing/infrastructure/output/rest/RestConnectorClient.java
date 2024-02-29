package net.gbm.devcenter.billing.infrastructure.output.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import net.gbm.devcenter.billing.infrastructure.output.rest.dtos.CustomRestResponseDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "products-api")
public interface RestConnectorClient {
    @GET
    @Path("/products")
    CustomRestResponseDTO getAllProducts(
            @QueryParam("limit") int limit,
            @QueryParam("skip") int skip
    );
}
