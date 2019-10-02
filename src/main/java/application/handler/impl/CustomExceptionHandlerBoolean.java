package application.handler.impl;

import application.exception.instance.CustomException;
import lombok.Getter;

@Getter
public class CustomExceptionHandlerBoolean extends CustomExceptionHandlerImpl {

    public boolean isCritical(CustomException e) {

        System.out.println("Exception successfully classified: " + e.getMessage() +
                " with " + (e.isCritical() ? "high" : "low") + " importance\n");

        return e.isCritical();
    }

}
