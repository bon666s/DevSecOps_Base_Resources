package net.gbm.devcenter.billing.domain.entities;

import lombok.Getter;

import java.util.List;
@Getter
public class Factura {
    List<Product> products;
    double subtotal;
    double totalDiscount;
    double tax;
    double totalDue;

    public Factura (List<Product> products) {
        this.products = products;
        this.processTotals();
    }

    public void add(Product product) {
        this.products.add(product);
        this.subtotal += product.getPrice();
        this.totalDiscount += product.getDiscountDeduction();
        this.tax += product.getTax();
        this.totalDue += product.getFinalPrice();
    }
    public boolean delete(Product product) {
        boolean deleted = this.products.remove(product);
        this.subtotal -= product.getPrice();
        this.totalDiscount -= product.getDiscountDeduction();
        this.tax -= product.getTax();
        this.totalDue -= product.getFinalPrice();
        return deleted;
    }
    private void processTotals() {
        for (Product product : products) {
            this.subtotal += product.getPrice();
            this.totalDiscount += product.getDiscountDeduction();
            this.tax += product.getTax();
            this.totalDue += product.getFinalPrice();
        }
    }
}
