package critical;

import application.exception.instance.BusinessValidationException;
import application.exception.instance.CustomException;
import application.exception.instance.ModelValidationException;
import application.handler.impl.CustomExceptionHandlerBoolean;
import application.handler.impl.CustomExceptionHandlerImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

@RunWith(Parameterized.class)
public class NotCriticalTest {

    public CustomExceptionHandlerImpl customExceptionHandlerImpl;


   @Parameterized.Parameter
   public CustomException customException;

    @Before
    public void setUp() throws Exception {

        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    @Parameterized.Parameters
    public static Collection<CustomException> data() {
        return Arrays.asList(new BusinessValidationException(), new ModelValidationException());
    }


    @Test
    public void notCriticalValidTest() {
        boolean critical = customExceptionHandlerImpl.isCritical(customException);

        assertFalse(critical);
    }

    @Test
    public void handleCritical() {
        assertEquals(HttpStatus.BAD_REQUEST, customExceptionHandlerImpl.handle(customException).getStatusCode());
    }

}
