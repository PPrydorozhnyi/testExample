package application.handler.impl;

import application.exception.instance.CustomException;
import application.model.responce.ErrorRecord;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
@Component("aliasHandler")
public class CustomExceptionHandlerAlias extends CustomExceptionHandlerImpl {

    private Environment environment;

    public CustomExceptionHandlerAlias(Environment environment) {
        this.environment = environment;
    }


    public boolean isCritical(Exception e) {

        CustomException exception = convertExceptionType(e);

        boolean critical = Boolean.valueOf(environment.getProperty(exception.getAlias()));

        System.out.println("Exception successfully classified: " + e.getMessage() +
                " with " + (critical ? "high" : "low") + " importance\n");

        return critical;
    }

    @Override
    public ResponseEntity<ErrorRecord> handle(Exception e) {
        ResponseEntity<ErrorRecord> handle;
        try {
            handle = super.handle(e);
        } catch (IllegalArgumentException illegal) {
            ++failedHandling;
            throw illegal;
        }
        return handle;
    }
}
