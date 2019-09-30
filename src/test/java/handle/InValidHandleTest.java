package handle;

import handler.ExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class InValidHandleTest {

//   public BusinessValidationException businessValidationException;
//   public ConnectionException connectionException;
//   public ServerException serverException;
//   public ModelValidationException modelValidationException;

   public ExceptionHandler exceptionHandler;


   @Parameterized.Parameter
   public Exception customException;

    @Before
    public void setUp() throws Exception {
//        businessValidationException = new BusinessValidationException();
//        connectionException = new ConnectionException();
//        serverException = new ServerException();
//        modelValidationException = new ModelValidationException();

        exceptionHandler = new ExceptionHandler();
    }

    @Parameterized.Parameters
    public static List<RuntimeException> data() {
        return Arrays.asList(new ArithmeticException(), new ArrayIndexOutOfBoundsException(), new ArrayStoreException());
    }


    @Test(expected = IllegalArgumentException.class)
    public void inValidTest() {
        exceptionHandler.handle(customException);
    }

}
