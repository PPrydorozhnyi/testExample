package application.exception.factory;

import application.exception.instance.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExceptionFactory {

    private final Environment environment;

    private List<CustomException> exceptions;

    public ExceptionFactory(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        exceptions = new ArrayList<>();

        exceptions.add(new BusinessValidationException());
        exceptions.add(new ConnectionException());
        exceptions.add(new ModelValidationException());
        exceptions.add(new ServerException());
    }

    public List<CustomException> getCriticalExceptions() {
        return exceptions.stream()
                .filter(exception -> Boolean.valueOf(environment.getProperty(exception.getAlias())))
                .collect(Collectors.toList());
    }

    public List<CustomException> getNotCriticalExceptions() {
        return exceptions.stream()
                .filter(exception -> !Boolean.valueOf(environment.getProperty(exception.getAlias())))
                .collect(Collectors.toList());
    }
}
