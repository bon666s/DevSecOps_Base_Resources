package net.gbm.devcenter.billing.application.usecases;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import net.gbm.devcenter.billing.application.interfaces.input.IFacturaService;
import net.gbm.devcenter.billing.application.interfaces.output.IProducts;
import net.gbm.devcenter.billing.domain.entities.Factura;
import net.gbm.devcenter.billing.domain.entities.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class FacturaServiceImplTest {
    @InjectMock
    IProducts mock;
    @Inject
    IFacturaService facturaService;

    @Test
    void shouldConstructFacturaWhenParametersAreValid() {
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
        List<Product> products = new ArrayList<>();
        products.add(temp);
        products.add(temp1);
        products.add(temp2);
        products.add(temp3);
        products.add(temp4);
        Mockito.when(mock.getAllProducts(5, 0)).thenReturn(products);
        QuarkusMock.installMockForType(mock, IProducts.class);
        Factura factura = facturaService.getFactura(5,0);
        assertEquals(factura.getClass(), Factura.class);
    }

}