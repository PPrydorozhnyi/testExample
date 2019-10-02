package handle;

import application.handler.CustomExceptionHandler;
import application.handler.impl.CustomExceptionHandlerBoolean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class InValidHandleTest {

    public CustomExceptionHandler customExceptionHandlerImpl;


   @Parameterized.Parameter
   public Exception customException;

    @Before
    public void setUp() throws Exception {

        customExceptionHandlerImpl = new CustomExceptionHandlerBoolean();
    }

    @Parameterized.Parameters
    public static List<RuntimeException> data() {
        return Arrays.asList(new ArithmeticException(), new ArrayIndexOutOfBoundsException(), new ArrayStoreException());
    }


    @Test(expected = IllegalArgumentException.class)
    public void inValidTest() {
        customExceptionHandlerImpl.handle(customException);
    }

}
