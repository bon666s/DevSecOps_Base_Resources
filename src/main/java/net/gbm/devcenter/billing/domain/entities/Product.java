package net.gbm.devcenter.billing.domain.entities;
import lombok.Getter;

@Getter
public class Product {
    private final int id;
    private final String title;
    private final double price;
    private final double discountPercentage;
    private final String brand;
    private final String category;
    private final String thumbnail;
    private final double discountDeduction;
    private final double tax;
    private final double finalPrice;


    public Product(int id, String title, double price, double discountPercentage, String brand, String category,
                   String thumbnail) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.discountDeduction = calculateDiscountDeduction();
        this.tax = calculateTax();
        this.finalPrice = calculateFinalPrice();
    }

    protected double calculateDiscountDeduction() {
        return (this.getPrice() * (this.getDiscountPercentage() * 0.01));
    }
    private double getPreTax() {
        return (this.getPrice() - this.calculateDiscountDeduction());
    }
    private double calculateTax() {
        double subtotal = this.getPrice() - this.calculateDiscountDeduction();
        if (this.category.equals("skincare")) {
            return (subtotal * 0.04);
        }
        if (this.category.equals("groceries")) {
            return (subtotal * 0.08);
        }
        return (subtotal * 0.13);
    }
    private double calculateFinalPrice() {
        return (this.getPreTax() + this.calculateTax());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product))
            return false;
        if (obj == this)
            return true;
        Product product = (Product) obj;
        return (this.getId() == product.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
