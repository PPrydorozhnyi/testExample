package application.exception.instance;

public class ServerException extends CustomException {
    public ServerException() {
        super("Server unexpected exception", true, "server");
    }
}
