package handle;

import application.exception.instance.*;
import application.handler.impl.CustomExceptionHandlerBoolean;
import application.handler.impl.CustomExceptionHandlerImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class ValidHandleTest {

    public CustomExceptionHandlerImpl customExceptionHandlerImpl;

    @Before
    public void setUp() throws Exception {
        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
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

        exceptions.forEach(exception -> customExceptionHandlerImpl.handle(exception));

        assertEquals(criticalAmount, customExceptionHandlerImpl.getCriticalSituationsCount());
        assertEquals(okAmount, customExceptionHandlerImpl.getOkSituationsCount());
    }


}
