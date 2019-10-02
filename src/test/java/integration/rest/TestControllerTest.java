package integration.rest;

import application.Application;
import application.exception.instance.BusinessValidationException;
import application.handler.CustomExceptionHandler;
import application.model.enums.ExceptionType;
import application.model.responce.ErrorRecord;
import application.service.DummyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import util.TestUtils;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
public class TestControllerTest {

    private final TestRestTemplate restTemplate = TestUtils.getTestRestTemplate();

    @LocalServerPort
    private int port;

    @Autowired
    private CustomExceptionHandler exceptionHandler;

    @MockBean
    private DummyService dummyService;

    @Test
    public void testRest() {

        doThrow(new BusinessValidationException()).when(dummyService).produceException(eq(ExceptionType.BUSINESS));

        ResponseEntity<ErrorRecord> errorRecordResponse = restTemplate
                .getForEntity(TestUtils.getBasicURL(port, "/tests/exception?type=") + ExceptionType.BUSINESS.getKeyName(), ErrorRecord.class);

        assertNotNull(errorRecordResponse);
        assertEquals(HttpStatus.BAD_REQUEST, errorRecordResponse.getStatusCode());
        ErrorRecord errorRecord = errorRecordResponse.getBody();
        assertNotNull(errorRecord);
        assertEquals(HttpStatus.BAD_REQUEST, errorRecord.getStatus());
        assertEquals(new BusinessValidationException().getMessage(), errorRecord.getMessage());

        verify(dummyService, times(1)).produceException(any());
    }

    @Test
    public void testRestWithException() {

        doThrow(new RuntimeException()).when(dummyService).produceException(any());


        restTemplate.exchange(TestUtils.getBasicURL(port, "/tests/exception?type=") + ExceptionType.MODEL.getKeyName(),
                HttpMethod.GET, null, String.class);

        assertEquals(1, exceptionHandler.getFailedHandling());

        verify(dummyService, times(1)).produceException(any());
    }

}
