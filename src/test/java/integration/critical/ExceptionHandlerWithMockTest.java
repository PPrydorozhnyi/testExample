package integration.critical;

import application.Application;
import application.exception.factory.ExceptionFactory;
import application.exception.instance.CustomException;
import application.handler.CustomExceptionHandler;
import application.handler.analyzer.CriticalAnalyzer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class ExceptionHandlerWithMockTest {

    @MockBean
    CriticalAnalyzer criticalAnalyzer;

    @Autowired
    private ExceptionFactory exceptionFactory;

    @Autowired
    private CustomExceptionHandler exceptionHandler;

    @Test
    public void testCriticalCount() {

        when(criticalAnalyzer.isCritical(any(CustomException.class))).thenReturn(true);

        List<CustomException> exceptions = exceptionFactory.getNotCriticalExceptions();

        //take not critical exception to show power of mock
        exceptions.forEach(exception -> exceptionHandler.handle(exception));

        assertEquals(exceptions.size(), exceptionHandler.getCriticalCount());
        assertEquals(0, exceptionHandler.getOkCount());
        assertEquals(0, exceptionHandler.getFailedHandling());

    }

    @Test
    public void testNotCriticalCount() {

        when(criticalAnalyzer.isCritical(any(CustomException.class))).thenReturn(false);

        List<CustomException> exceptions = exceptionFactory.getCriticalExceptions();

        //take not critical exception to show power of mock
        exceptions.forEach(exception -> exceptionHandler.handle(exception));

        assertEquals(exceptions.size(), exceptionHandler.getOkCount());
        assertEquals(0, exceptionHandler.getCriticalCount());
        assertEquals(0, exceptionHandler.getFailedHandling());

    }

}
