package exception.instances;

public class BusinessValidationException extends CustomException {
    public BusinessValidationException() {
        super("Can not execute business logic", false);
    }
}
