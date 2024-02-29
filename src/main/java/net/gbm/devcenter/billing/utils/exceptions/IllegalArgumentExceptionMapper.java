package net.gbm.devcenter.billing.utils.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import net.gbm.devcenter.billing.utils.exceptions.dtos.ErrorResponse;

import java.util.UUID;

@Provider
@SuppressWarnings("unused")
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException e) {
        String errorId = UUID.randomUUID().toString();
        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
