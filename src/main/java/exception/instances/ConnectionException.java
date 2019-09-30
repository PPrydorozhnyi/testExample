package exception.instances;

public class ConnectionException extends CustomException {
     public ConnectionException() {
        super("Refused connection exception", true);
    }
}
