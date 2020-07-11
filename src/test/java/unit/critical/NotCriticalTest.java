package unit.critical;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import application.exception.instance.BusinessValidationException;
import application.exception.instance.CustomException;
import application.exception.instance.ModelValidationException;
import application.handler.impl.CustomExceptionHandlerBoolean;
import application.handler.impl.CustomExceptionHandlerImpl;

class NotCriticalTest {

    public CustomExceptionHandlerImpl customExceptionHandlerImpl;

    @BeforeEach
    public void setUp() {

        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    private static Stream<CustomException> data() {
        return Stream.of(new BusinessValidationException(), new ModelValidationException());
    }


    @ParameterizedTest
    @MethodSource("data")
    void notCriticalValidTest(CustomException customException) {
        boolean critical = customExceptionHandlerImpl.isCritical(customException);

        Assertions.assertFalse(critical);
    }

    @ParameterizedTest
    @MethodSource("data")
    void handleCritical(CustomException customException) {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, customExceptionHandlerImpl.handle(customException).getStatusCode());
    }

}
