package net.gbm.devcenter.billing.infrastructure.output.rest.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomRestResponseDTO {
    List<ProductDTO> products;
    private int total;
    private int skip;
    private int limit;

}
