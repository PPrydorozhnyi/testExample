package integration.critical;

import application.Application;
import application.exception.factory.ExceptionFactory;
import application.handler.analyzer.CriticalAnalyzer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
public class CriticalAnalyzerTest {

    @Autowired
    CriticalAnalyzer criticalAnalyzer;

    @Autowired
    private ExceptionFactory exceptionFactory;

    @Test
    public void testCritical() {

        exceptionFactory.getCriticalExceptions()
                .forEach(exception -> assertTrue(criticalAnalyzer.isCritical(exception)));

    }

    @Test
    public void testNotCritical() {

        exceptionFactory.getNotCriticalExceptions()
                .forEach(exception -> assertFalse(criticalAnalyzer.isCritical(exception)));

    }
}
