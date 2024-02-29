package net.gbm.devcenter.billing.infrastructure.input.rest.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RestAPIResponseDTO {
    List<ProductDTO> products;
    double subtotal;
    double totalDiscount;
    double tax;
    double total;
}
