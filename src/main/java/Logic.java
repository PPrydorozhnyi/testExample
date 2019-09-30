import lombok.Data;

@Data
public class Logic {

    public static int calculateSquare(int length, int width) {
        if (length <= 0 || width <= 0) {
            throw new ArithmeticException("Incorrect input values");
        }
        return length * width;
    }

    private Logic() {}
}
