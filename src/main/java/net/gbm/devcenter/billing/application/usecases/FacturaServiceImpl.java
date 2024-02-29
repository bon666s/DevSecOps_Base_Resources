package net.gbm.devcenter.billing.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.gbm.devcenter.billing.application.interfaces.input.IFacturaService;
import net.gbm.devcenter.billing.application.interfaces.output.IProducts;
import net.gbm.devcenter.billing.domain.entities.Factura;

@ApplicationScoped
public class FacturaServiceImpl implements IFacturaService {
    @Inject
    IProducts iProductos;

    public Factura getFactura(int limit, int skip) {
        //validateLimitAndSkip(limit, skip);
        return new Factura(iProductos.getAllProducts(limit, skip));
    }

    /*private void validateLimitAndSkip(int limit, int skip) {
        if (limit < 1 || limit > 100) {
            throw new IllegalArgumentException("Limit must be within 1 and 100.");
        }
        if (skip < 0 || skip > 100) {
            throw new IllegalArgumentException("Skip must be within 0 and 99.");
        }
        int total = limit + skip;
        if (total > 100) {
            throw new IllegalArgumentException("The sum of limit and skip must be within 1 and 100.");
        }
    }*/
}
