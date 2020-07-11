package unit.critical;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import application.exception.instance.ConnectionException;
import application.exception.instance.CustomException;
import application.exception.instance.ServerException;
import application.handler.CustomExceptionHandler;
import application.handler.impl.CustomExceptionHandlerBoolean;

class CriticalTest {

    private static CustomExceptionHandler customExceptionHandlerImpl;

    @BeforeAll
    public static void setUp() {
        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    private static Stream<CustomException> data() {
        return Stream.of(new ConnectionException(), new ServerException());
    }

    @ParameterizedTest
    @MethodSource("data")
    void isCriticalValidTest(CustomException customException) {
        boolean critical = customExceptionHandlerImpl.isCritical(customException);
        Assertions.assertTrue(critical);
    }

    @ParameterizedTest
    @MethodSource("data")
    void handleCritical(CustomException customException) {
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                customExceptionHandlerImpl.handle(customException).getStatusCode());
    }

}
