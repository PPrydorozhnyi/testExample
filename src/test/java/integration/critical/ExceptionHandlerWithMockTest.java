package integration.critical;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import application.Application;
import application.exception.factory.ExceptionFactory;
import application.exception.instance.CustomException;
import application.handler.CustomExceptionHandler;
import application.handler.analyzer.CriticalAnalyzer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ExceptionHandlerWithMockTest {

    @MockBean
    CriticalAnalyzer criticalAnalyzer;

    @Autowired
    private ExceptionFactory exceptionFactory;

    @Autowired
    private CustomExceptionHandler exceptionHandler;

    @Test
    void testCriticalCount() {

        Mockito.when(criticalAnalyzer.isCritical(ArgumentMatchers.any(CustomException.class)))
                .thenReturn(true);

        List<CustomException> exceptions = exceptionFactory.getNotCriticalExceptions();

        //take not critical exception to show power of mock
        exceptions.forEach(exception -> exceptionHandler.handle(exception));

        Assertions.assertEquals(exceptions.size(), exceptionHandler.getCriticalCount());
        Assertions.assertEquals(0, exceptionHandler.getOkCount());
        Assertions.assertEquals(0, exceptionHandler.getFailedHandling());

    }

    @Test
    void testNotCriticalCount() {

        Mockito.when(criticalAnalyzer.isCritical(ArgumentMatchers.any(CustomException.class)))
                .thenReturn(false);

        List<CustomException> exceptions = exceptionFactory.getCriticalExceptions();

        //take not critical exception to show power of mock
        exceptions.forEach(exception -> exceptionHandler.handle(exception));

        Assertions.assertEquals(exceptions.size(), exceptionHandler.getOkCount());
        Assertions.assertEquals(0, exceptionHandler.getCriticalCount());
        Assertions.assertEquals(0, exceptionHandler.getFailedHandling());

    }

}
