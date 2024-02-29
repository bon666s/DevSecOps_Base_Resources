package net.gbm.devcenter.billing.infrastructure.input.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import net.gbm.devcenter.billing.application.interfaces.input.IFacturaService;
import net.gbm.devcenter.billing.domain.entities.Factura;
import net.gbm.devcenter.billing.domain.entities.Product;
import net.gbm.devcenter.billing.infrastructure.input.rest.dtos.ProductDTO;
import net.gbm.devcenter.billing.infrastructure.input.rest.dtos.RestAPIResponseDTO;
import net.gbm.devcenter.billing.utils.exceptions.dtos.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Slf4j
class RestAPITest {
    @InjectMock
    IFacturaService facturaService;

    @BeforeEach
    void setUp() {
        Product temp = new Product(0, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        Product temp1 = new Product(1, "Test", 100.0, 10, "Testing Ground",
                "Test", "Image");
        List<Product> products = new ArrayList<>();
        products.add(temp);
        products.add(temp1);

        Factura factura = new Factura(products);
        Mockito.when(facturaService.getFactura(2,0)).thenReturn(factura);
        Mockito.when(facturaService.getFactura(101,0)).thenThrow(new IllegalArgumentException("Illegal Test Message"));
        Mockito.when(facturaService.getFactura(1000,0)).thenThrow(new IndexOutOfBoundsException("Throwable Test Message"));
        QuarkusMock.installMockForType(facturaService, IFacturaService.class);
    }

    @Test
    void shouldReturnJsonWithResponseWhenCalledCorrectly() {
        RestAPIResponseDTO temp = new RestAPIResponseDTO();
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(0);
        productDTO.setTitle("Test");
        productDTO.setCategory("Test");
        productDTO.setBrand("Testing Ground");
        productDTO.setThumbnail("Image");
        productDTO.setDiscountPercentage(10);
        productDTO.setDiscountDeduction(10);
        productDTO.setPrice(101.7);
        list.add(productDTO);
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1);
        productDTO1.setTitle("Test");
        productDTO1.setCategory("Test");
        productDTO1.setBrand("Testing Ground");
        productDTO1.setThumbnail("Image");
        productDTO1.setDiscountPercentage(10);
        productDTO1.setDiscountDeduction(10);
        productDTO1.setPrice(101.7);
        list.add(productDTO1);
        temp.setProducts(list);
        temp.setTotal(203.4);
        temp.setSubtotal(200.0);
        temp.setTotalDiscount(20);
        temp.setTax(23.400000000000002);
        RestAPIResponseDTO result = given()
                .when()
                .get("/factura?limit=2&skip=0")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(RestAPIResponseDTO.class);
        assertEquals(temp, result);
    }

    @Test
    void shouldReturnJsonWithErrorWhenCalledIncorrectly() {
        log.info("Test Illegal Argument Exception");
        ErrorResponse result = given()
                .when()
                .get("/factura?limit=101&skip=0")
                .then()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(ErrorResponse.class);
        log.info("Test Illegal Argument Exception");
        assertEquals("Illegal Test Message", result.getErrors().get(0).getMessage());
    }
    @Test
    void shouldReturnJsonWithErrorWhenSomethingUnexpectedHappens() {
        log.info("Test Throwable");
        ErrorResponse result = given()
                .when().get("/factura?limit=1000&skip=0")
                .then()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(ErrorResponse.class);
        log.info("Test Throwable");
        assertEquals("Throwable Test Message", result.getErrors().get(0).getMessage());
    }
}