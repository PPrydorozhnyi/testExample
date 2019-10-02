package application.handler.impl;

import application.exception.instance.CustomException;
import application.handler.CustomExceptionHandler;
import application.model.responce.ErrorRecord;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public abstract class CustomExceptionHandlerImpl implements CustomExceptionHandler {

    private int okSituationsCount = 0;
    private int criticalSituationsCount = 0;
    protected int failedHandling = 0;

    public ResponseEntity<ErrorRecord> handle(Exception e) {

        CustomException exception = convertExceptionType(e);
        HttpStatus status;

        if (isCritical(exception)) {
            ++criticalSituationsCount;
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        } else {
            ++okSituationsCount;
            status = HttpStatus.BAD_REQUEST;
        }

        System.out.println(e.getClass().getName() + " successfully handled\n");
        System.out.println("Ok exceptions: " + okSituationsCount);
        System.out.println("Critical exceptions: " + criticalSituationsCount);

        return ResponseEntity.status(status).body(new ErrorRecord(status, exception.getMessage()));
    }

    CustomException convertExceptionType(Exception e) {
        CustomException exception;
        if (e instanceof CustomException) {
            exception = (CustomException) e;
        } else {
            throw new IllegalArgumentException();
        }
        return exception;
    }
}
