package unit.handle;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import application.handler.CustomExceptionHandler;
import application.handler.impl.CustomExceptionHandlerBoolean;

class InValidHandleTest {

    private static CustomExceptionHandler customExceptionHandlerImpl;

    @BeforeAll
    static void setUp() {
        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    @ParameterizedTest
    @MethodSource("provideCustomExceptions")
    void inValidTest(RuntimeException customException) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> customExceptionHandlerImpl.handle(customException));
    }

    private static Stream<RuntimeException> provideCustomExceptions() {
        return Stream.of(new ArithmeticException(), new ArrayIndexOutOfBoundsException(), new ArrayStoreException());
    }

}
