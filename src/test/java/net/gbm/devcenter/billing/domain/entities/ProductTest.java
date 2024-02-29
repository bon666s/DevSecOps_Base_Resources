package net.gbm.devcenter.billing.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductTest {

    @Test
    void shouldCalculateDiscountWhenCreated() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        assertEquals(10, temp.getDiscountDeduction());
    }
    @Test
    void shouldTax13PercentWhenCategoryIsOther() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        assertEquals(11.700000000000001, temp.getTax());
    }
    @Test
    void shouldTax4PercentWhenCategoryIsSkincare() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "skincare", "Image");
        assertEquals(3.6, temp.getTax());
    }
    @Test
    void shouldTax8PercentWhenCategoryIsGroceries() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "groceries", "Image");
        assertEquals(7.2, temp.getTax());
    }
    @Test
    void shouldCalculateFinalPriceWhenCreated() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        assertEquals(101.7, temp.getFinalPrice());
    }

    @Test
    void shouldCallCorrectMethodWhenCalled() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        Object test = new Object();
        assertNotEquals(temp, test);
        Product test2 = new Product(0, "This Is It", 100.0, 10, "Second",
                "Test", "Image");
        assertEquals(temp, test2);
        assertEquals(temp.hashCode(), temp.hashCode());
    }
    @Test
    void shouldReturnValuesWhenGettersAreCalled() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        assertEquals("Test", temp.getTitle());
        assertEquals("Testing Ground", temp.getBrand());
        assertEquals("Test", temp.getCategory());
        assertEquals("Image", temp.getThumbnail());

    }
}