package net.gbm.devcenter.billing.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class FacturaTest {
    Factura factura;
    List<Product> products;
    @BeforeEach
    void setUp() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        Product temp1 = new Product(1, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        Product temp2 = new Product(2, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        Product temp3 = new Product(3, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        Product temp4 = new Product(4, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        this.products = new ArrayList<>();
        products.add(temp);
        products.add(temp1);
        products.add(temp2);
        products.add(temp3);
        products.add(temp4);
        this.factura = new Factura(products);
    }
    @Test
    void shouldProcessTotalsWhenCreated() {
        assertEquals(this.products, factura.getProducts());
        assertEquals(500, factura.getSubtotal()) ;
        assertEquals(50, factura.getTotalDiscount());
        assertEquals(58.50000000000001, factura.getTax());
        assertEquals(508.5, factura.getTotalDue());
    }
    @Test
    void shouldProcessTotalsWhenAddingNewProduct() {
        Product temp = new Product(4, "Adding", 10.0, 0, "Testing Ground",
                "Test", "Image");
        factura.add(temp);
        assertEquals(510, factura.getSubtotal()) ;
        assertEquals(50, factura.getTotalDiscount());
        assertEquals(59.800000000000004, factura.getTax());
        assertEquals(519.8, factura.getTotalDue());
    }
    @Test
    void shouldProcessTotalsWhenARemovingProduct() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        factura.delete(temp);
        assertEquals(400, factura.getSubtotal()) ;
        assertEquals(40, factura.getTotalDiscount());
        assertEquals(46.800000000000004, factura.getTax());
        assertEquals(406.8, factura.getTotalDue());
    }

}
