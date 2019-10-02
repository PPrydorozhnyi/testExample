package application.handler;

import application.model.responce.ErrorRecord;
import org.springframework.http.ResponseEntity;

public interface CustomExceptionHandler {
    boolean isCritical(Exception e);

    ResponseEntity<ErrorRecord> handle(Exception e);
}
