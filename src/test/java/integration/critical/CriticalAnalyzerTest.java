package integration.critical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import application.Application;
import application.exception.factory.ExceptionFactory;
import application.handler.analyzer.CriticalAnalyzer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
class CriticalAnalyzerTest {

    @Autowired
    CriticalAnalyzer criticalAnalyzer;

    @Autowired
    private ExceptionFactory exceptionFactory;

    @Test
    void testCritical() {

        exceptionFactory.getCriticalExceptions()
                .forEach(exception -> Assertions.assertTrue(criticalAnalyzer.isCritical(exception)));

    }

    @Test
    void testNotCritical() {

        exceptionFactory.getNotCriticalExceptions()
                .forEach(exception -> Assertions.assertFalse(criticalAnalyzer.isCritical(exception)));

    }
}
