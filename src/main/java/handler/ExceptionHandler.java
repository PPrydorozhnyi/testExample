package handler;

import exception.instances.CustomException;
import lombok.Getter;
import model.ErrorRecord;
import org.apache.http.HttpStatus;

@Getter
public class ExceptionHandler {

    private int okSituationsCount = 0;
    private int criticalSituationsCount = 0;


    public boolean isCritical(Exception e) {

        CustomException exception = convertExceptionType(e);

        System.out.println("Exception successfully classified: " + e.getMessage() +
                " with " + (exception.isCritical() ? "high" : "low") + " importance\n");

        return exception.isCritical();
    }

    public ErrorRecord handle(Exception e) {

        CustomException exception = convertExceptionType(e);
        int status;

        if (exception.isCritical()) {
            ++criticalSituationsCount;
            status = HttpStatus.SC_INTERNAL_SERVER_ERROR;

        } else {
            ++okSituationsCount;
            status = HttpStatus.SC_BAD_REQUEST;
        }

        System.out.println(e.getClass().getName() + " successfully handled\n");
        System.out.println("Ok exceptions: " + okSituationsCount);
        System.out.println("Critical exceptions: " + criticalSituationsCount);

        return new ErrorRecord(status, exception.getMessage());
    }

    private CustomException convertExceptionType(Exception e) {
        CustomException exception;
        if (e instanceof CustomException) {
            exception = (CustomException) e;
        } else {
            throw new IllegalArgumentException();
        }
        return exception;
    }
}
