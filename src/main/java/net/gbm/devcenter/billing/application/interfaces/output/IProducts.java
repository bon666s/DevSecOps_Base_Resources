package net.gbm.devcenter.billing.application.interfaces.output;

import net.gbm.devcenter.billing.domain.entities.Product;

import java.util.List;

public interface IProducts {
    List<Product> getAllProducts(int limit, int skip);
}
