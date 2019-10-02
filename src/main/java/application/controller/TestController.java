package application.controller;

import application.handler.CustomExceptionHandler;
import application.model.enums.ExceptionType;
import application.model.responce.ErrorRecord;
import application.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
public class TestController {

    private DummyService dummyService;
    private CustomExceptionHandler customExceptionHandler;

    @Autowired
    public TestController(DummyService dummyService, @Qualifier("aliasHandler") CustomExceptionHandler customExceptionHandler) {
        this.dummyService = dummyService;
        this.customExceptionHandler = customExceptionHandler;
    }

    @GetMapping("/exception")
    public String getException(@RequestParam(name = "type", required = false) ExceptionType exceptionType) {
        dummyService.produceException(exceptionType);
        return "gocha";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRecord> handle(Exception e) {
        return customExceptionHandler.handle(e);
    }
}
