package handle;

import exception.instances.*;
import handler.ExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class ValidHandleTest {

    public ExceptionHandler exceptionHandler;

    @Before
    public void setUp() throws Exception {
        exceptionHandler = new ExceptionHandler();
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, 0, Arrays.asList(new BusinessValidationException(), new ModelValidationException())},
                {0, 2, Arrays.asList(new ConnectionException(), new ServerException())},
                {2, 2, Arrays.asList(new BusinessValidationException(), new ModelValidationException(),
                        new ConnectionException(), new ServerException())}
    });
    }

    @Parameterized.Parameter
    public int okAmount;

    @Parameterized.Parameter(1)
    public int criticalAmount;

    @Parameterized.Parameter(2)
    public List<CustomException> exceptions;

    @Test
    public void validateParameters() {

        exceptions.forEach(exception -> exceptionHandler.handle(exception));

        assertEquals(criticalAmount, exceptionHandler.getCriticalSituationsCount());
        assertEquals(okAmount, exceptionHandler.getOkSituationsCount());
    }


}
