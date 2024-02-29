package net.gbm.devcenter.billing.infrastructure.input.rest;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.gbm.devcenter.billing.application.interfaces.input.IFacturaService;
import net.gbm.devcenter.billing.domain.entities.Factura;
import net.gbm.devcenter.billing.domain.entities.Product;
import net.gbm.devcenter.billing.infrastructure.input.rest.dtos.ProductDTO;
import net.gbm.devcenter.billing.infrastructure.input.rest.dtos.RestAPIResponseDTO;
import net.gbm.devcenter.billing.utils.exceptions.dtos.ErrorResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;

@Path("/factura")
@APIResponse(responseCode = "400", description = "Bad request",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
        ))
@APIResponse(responseCode = "500", description = "Internal server error",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
        ))


public class RestAPI {
    @Inject
    IFacturaService facturaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create Invoice from product range")
    @APIResponse(
            description = "Create Invoice from product range",
            responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RestAPIResponseDTO.class)
            )
    )
    public Response getDefinedProducts(@NotNull @QueryParam("limit") int limit, @NotNull @QueryParam("skip")  int skip) {
        return Response.ok(this.create(facturaService.getFactura(limit, skip))).build();
    }

    private RestAPIResponseDTO create(Factura factura) {
        RestAPIResponseDTO restAPIResponseDTO = new RestAPIResponseDTO();
        restAPIResponseDTO.setProducts(new ArrayList<>());
        for (Product product : factura.getProducts()) {
            ProductDTO temp = new ProductDTO();
            temp.setId(product.getId());
            temp.setTitle(product.getTitle());
            temp.setCategory(product.getCategory());
            temp.setBrand(product.getBrand());
            temp.setThumbnail(product.getThumbnail());
            temp.setDiscountPercentage(product.getDiscountPercentage());
            temp.setDiscountDeduction(product.getDiscountDeduction());
            temp.setPrice(product.getFinalPrice());
            restAPIResponseDTO.getProducts().add(temp);
        }

        restAPIResponseDTO.setSubtotal(factura.getSubtotal());
        restAPIResponseDTO.setTotalDiscount(factura.getTotalDiscount());
        restAPIResponseDTO.setTax(factura.getTax());
        restAPIResponseDTO.setTotal(factura.getTotalDue());
        return restAPIResponseDTO;
    }
}