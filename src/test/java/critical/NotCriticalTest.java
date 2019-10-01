package critical;

import exception.instances.BusinessValidationException;
import exception.instances.CustomException;
import exception.instances.ModelValidationException;
import handler.ExceptionHandler;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

@RunWith(Parameterized.class)
public class NotCriticalTest {

   public ExceptionHandler exceptionHandler;


   @Parameterized.Parameter
   public CustomException customException;

    @Before
    public void setUp() throws Exception {

        exceptionHandler = new ExceptionHandler();
    }

    @Parameterized.Parameters
    public static Collection<CustomException> data() {
        return Arrays.asList(new BusinessValidationException(), new ModelValidationException());
    }


    @Test
    public void notCriticalValidTest() {
        boolean critical = exceptionHandler.isCritical(customException);

        assertFalse(critical);
    }

    @Test
    public void handleCritical() {
        assertEquals(HttpStatus.SC_BAD_REQUEST, exceptionHandler.handle(customException).getStatus());
    }

}
