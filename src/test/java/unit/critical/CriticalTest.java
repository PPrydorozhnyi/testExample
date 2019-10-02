package unit.critical;

import application.exception.instance.ConnectionException;
import application.exception.instance.CustomException;
import application.exception.instance.ServerException;
import application.handler.CustomExceptionHandler;
import application.handler.impl.CustomExceptionHandlerBoolean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class CriticalTest {


    public CustomExceptionHandler customExceptionHandlerImpl;


   @Parameterized.Parameter
   public CustomException customException;

    @Before
    public void setUp() {

        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    @Parameterized.Parameters
    public static Collection<CustomException> data() {
        return Arrays.asList(new ConnectionException(), new ServerException());
    }


    @Test
    public void isCriticalValidTest() {
        boolean critical = customExceptionHandlerImpl.isCritical(customException);

        assertTrue(critical);
    }

    @Test
    public void handleCritical() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, customExceptionHandlerImpl.handle(customException).getStatusCode());
    }

}
