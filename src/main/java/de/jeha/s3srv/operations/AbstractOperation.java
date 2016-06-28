package de.jeha.s3srv.operations;

import de.jeha.s3srv.api.ErrorCodes;
import de.jeha.s3srv.api.ErrorResponse;
import de.jeha.s3srv.jaxb.JaxbMarshaller;
import de.jeha.s3srv.storage.StorageBackend;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public abstract class AbstractOperation {

    private final StorageBackend storageBackend;

    protected AbstractOperation(StorageBackend storageBackend) {
        this.storageBackend = storageBackend;
    }

    protected StorageBackend getStorageBackend() {
        return storageBackend;
    }

    protected Response buildErrorResponse(ErrorCodes errorCode, String resource, String requestId) {
        ErrorResponse error = new ErrorResponse(errorCode.getCode(), errorCode.getDescription(), resource, requestId);
        try {
            String errorResponseXml = JaxbMarshaller.marshall(error);
            return Response.status(errorCode.getStatusCode()).entity(errorResponseXml).build();
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
