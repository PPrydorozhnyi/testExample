package unit.handle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import application.exception.instance.BusinessValidationException;
import application.exception.instance.ConnectionException;
import application.exception.instance.CustomException;
import application.exception.instance.ModelValidationException;
import application.exception.instance.ServerException;
import application.handler.impl.CustomExceptionHandlerBoolean;
import application.handler.impl.CustomExceptionHandlerImpl;

class ValidHandleTest {

    private CustomExceptionHandlerImpl customExceptionHandlerImpl;

    @BeforeEach
    public void setUp() {
        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(2, 0, Arrays.asList(new BusinessValidationException(), new ModelValidationException())),
                Arguments.of(0, 2, Arrays.asList(new ConnectionException(), new ServerException())),
                Arguments.of(2, 2, Arrays.asList(new BusinessValidationException(),
                        new ModelValidationException(),
                        new ConnectionException(), new ServerException())));
    }

    @ParameterizedTest
    @MethodSource("data")
    void validateParameters(int okAmount, int criticalAmount, List<CustomException> exceptions) {
        exceptions.forEach(exception -> customExceptionHandlerImpl.handle(exception));
        Assertions.assertEquals(criticalAmount, customExceptionHandlerImpl.getCriticalSituationsCount());
        Assertions.assertEquals(okAmount, customExceptionHandlerImpl.getOkSituationsCount());
    }

}
