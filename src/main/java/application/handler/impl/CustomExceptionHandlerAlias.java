package application.handler.impl;

import application.exception.instance.CustomException;
import application.handler.analyzer.CriticalAnalyzer;
import application.model.responce.ErrorRecord;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
@Component("aliasHandler")
public class CustomExceptionHandlerAlias extends CustomExceptionHandlerImpl {

    private CriticalAnalyzer criticalAnalyzer;

    public CustomExceptionHandlerAlias(CriticalAnalyzer criticalAnalyzer) {
        this.criticalAnalyzer = criticalAnalyzer;
    }

    @Override
    public boolean isCritical(CustomException e) {
        return criticalAnalyzer.isCritical(e);
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
