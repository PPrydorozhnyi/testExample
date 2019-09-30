import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LogicTest {

    @Test
    public void calculateSquareValid() {
        int length = 10;
        int width = 5;

        int expectedResult = 50;

        int result = Logic.calculateSquare(length, width);
        assertEquals(expectedResult, result);
    }

    @Test(expected = ArithmeticException.class)
    public void calculateSquareInvalid() {
        int length = 0;
        int width = -1;
        Logic.calculateSquare(length, width);
    }
}
