package application.handler.impl;

import application.exception.instance.CustomException;
import lombok.Getter;

@Getter
public class CustomExceptionHandlerBoolean extends CustomExceptionHandlerImpl {

    public boolean isCritical(Exception e) {

        CustomException exception = convertExceptionType(e);

        System.out.println("Exception successfully classified: " + e.getMessage() +
                " with " + (exception.isCritical() ? "high" : "low") + " importance\n");

        return exception.isCritical();
    }

}
