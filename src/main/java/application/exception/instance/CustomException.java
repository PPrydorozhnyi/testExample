package application.exception.instance;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private boolean critical;
    private String alias;

    public CustomException(String message, boolean isCritical, String alias) {
        super(message);
        this.critical = isCritical;
        this.alias = alias;
    }

}
