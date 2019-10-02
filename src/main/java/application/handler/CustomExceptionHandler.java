package application.handler;

import application.exception.instance.CustomException;
import application.model.responce.ErrorRecord;
import org.springframework.http.ResponseEntity;

public interface CustomExceptionHandler {
    boolean isCritical(CustomException e);

    ResponseEntity<ErrorRecord> handle(Exception e);
}
