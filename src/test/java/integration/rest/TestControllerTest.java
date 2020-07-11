package integration.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import application.Application;
import application.exception.instance.BusinessValidationException;
import application.handler.CustomExceptionHandler;
import application.model.enums.ExceptionType;
import application.model.responce.ErrorRecord;
import application.service.DummyService;
import util.TestUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
class TestControllerTest {

    private final TestRestTemplate restTemplate = TestUtils.getTestRestTemplate();

    @LocalServerPort
    private int port;

    @Autowired
    private CustomExceptionHandler exceptionHandler;

    @MockBean
    private DummyService dummyService;

    @Test
    void testRest() {

        Mockito.doThrow(new BusinessValidationException())
                .when(dummyService).produceException(ArgumentMatchers.eq(ExceptionType.BUSINESS));

        ResponseEntity<ErrorRecord> errorRecordResponse = restTemplate
                .getForEntity(TestUtils.getBasicURL(port, "/tests/exception?type=") + ExceptionType.BUSINESS.getKeyName(), ErrorRecord.class);

        Assertions.assertNotNull(errorRecordResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorRecordResponse.getStatusCode());
        ErrorRecord errorRecord = errorRecordResponse.getBody();
        Assertions.assertNotNull(errorRecord);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorRecord.getStatus());
        Assertions.assertEquals(new BusinessValidationException().getMessage(), errorRecord.getMessage());

        Mockito.verify(dummyService, Mockito.times(1))
                .produceException(ArgumentMatchers.any());
    }

    @Test
    void testRestWithException() {

        Mockito.doThrow(new RuntimeException()).when(dummyService)
                .produceException(ArgumentMatchers.any());


        restTemplate.exchange(TestUtils.getBasicURL(port, "/tests/exception?type=") + ExceptionType.MODEL.getKeyName(),
                HttpMethod.GET, null, String.class);

        Assertions.assertEquals(1, exceptionHandler.getFailedHandling());

        Mockito.verify(dummyService, Mockito.times(1))
                .produceException(ArgumentMatchers.any());
    }

}
