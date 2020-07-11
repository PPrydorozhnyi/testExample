package integration.exception_factory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import application.Application;
import application.exception.factory.ExceptionFactory;
import application.exception.instance.CustomException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
class ExceptionFactoryIntTest {

    private static final List<String> CRITICAL_EXCEPTIONS = Arrays.asList("connection", "server");
    private static final List<String> NOT_CRITICAL_EXCEPTIONS = Arrays.asList("business", "model");

    @Autowired
    private ExceptionFactory exceptionFactory;


    @Test
    void exceptionFactoryCriticalTest() {
        List<String> collect = exceptionFactory.getCriticalExceptions().stream()
                .map(CustomException::getAlias).collect(Collectors.toList());

        Assertions.assertEquals(2, collect.size());
        Assertions.assertEquals(CRITICAL_EXCEPTIONS, collect);

    }

    @Test
    void exceptionFactoryNotCriticalTest() {
        List<String> collect = exceptionFactory.getNotCriticalExceptions().stream()
                .map(CustomException::getAlias).collect(Collectors.toList());

        Assertions.assertEquals(2, collect.size());
        Assertions.assertEquals(NOT_CRITICAL_EXCEPTIONS, collect);

    }

}
