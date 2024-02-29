package net.gbm.devcenter.billing.infrastructure.output.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import net.gbm.devcenter.billing.application.interfaces.output.IProducts;
import net.gbm.devcenter.billing.domain.entities.Product;
import net.gbm.devcenter.billing.infrastructure.output.rest.dtos.ProductDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class RestDummyJsonImpl implements IProducts {
    @Inject
    @RestClient
    RestConnectorClient restConnectorClient;

    @Override
    public List<Product> getAllProducts(int limit, int skip) {
        List<ProductDTO> dtos = restConnectorClient.getAllProducts(limit, skip).getProducts();
        return convertToProduct(dtos);
    }

    private List<Product> convertToProduct(List<ProductDTO> dtos) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO product : dtos)  {
            if (product.getStock() != 0){
                products.add(new Product(product.getId(), product.getTitle(), product.getPrice(),
                        product.getDiscountPercentage(), product.getBrand(), product.getCategory(),
                        product.getThumbnail()));
            }
        }
        return products;
    }
}
