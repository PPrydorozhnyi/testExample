package critical;

import exception.instances.ConnectionException;
import exception.instances.CustomException;
import exception.instances.ServerException;
import handler.ExceptionHandler;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class CriticalTest {

//   public BusinessValidationException businessValidationException;
//   public ConnectionException connectionException;
//   public ServerException serverException;
//   public ModelValidationException modelValidationException;

   public ExceptionHandler exceptionHandler;


   @Parameterized.Parameter
   public CustomException customException;

    @Before
    public void setUp() throws Exception {
//        businessValidationException = new BusinessValidationException();
//        connectionException = new ConnectionException();
//        serverException = new ServerException();
//        modelValidationException = new ModelValidationException();

        exceptionHandler = new ExceptionHandler();
    }

    @Parameterized.Parameters
    public static Collection<CustomException> data() {
        return Arrays.asList(new ConnectionException(), new ServerException());
    }


    @Test
    public void isCriticalValidTest() {
        boolean critical = exceptionHandler.isCritical(customException);

        assertTrue(critical);
    }

    @Test
    public void handleCritical() {
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, exceptionHandler.handle(customException).getStatus());
    }

}
