package application.exception.instance;

public class ConnectionException extends CustomException {
    public ConnectionException() {
        super("Refused connection application.exception", true, "connection");
    }
}
