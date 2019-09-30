package exception.instances;

public class ModelValidationException extends CustomException {
    public ModelValidationException() {
        super("Incorrect parameters was passed", false);
    }
}
