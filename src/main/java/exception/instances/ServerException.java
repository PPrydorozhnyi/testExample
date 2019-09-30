package exception.instances;

public class ServerException extends CustomException {
    public ServerException() {
        super("Server unexpected exception", true);
    }
}
