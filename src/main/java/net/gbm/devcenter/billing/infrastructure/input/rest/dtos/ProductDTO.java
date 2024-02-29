package net.gbm.devcenter.billing.infrastructure.input.rest.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String title;
    private String category;
    private String brand;
    private String thumbnail;
    private double discountPercentage;
    private double discountDeduction;
    private double price;
}
