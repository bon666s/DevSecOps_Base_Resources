package net.gbm.devcenter.billing.application.interfaces.input;

import net.gbm.devcenter.billing.application.interfaces.validators.ProductRangeValidate;
import net.gbm.devcenter.billing.domain.entities.Factura;

public interface IFacturaService {

    @ProductRangeValidate
    Factura getFactura(int limit, int skip);
}
