package application.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExceptionType {
    BUSINESS("BusinessValidationException"),
    SERVER("ServerException"),
    CONNECTION("ConnectionException"),
    MODEL("ModelValidationException"),
    DEFAULT("default");

    private String keyName;

    ExceptionType(String keyName) {
        this.keyName = keyName;
    }

    @JsonCreator
    public ExceptionType fromString(String value) {
        return Arrays.stream(ExceptionType.values())
                .filter(type -> type.keyName.equalsIgnoreCase(value))
                .findAny().orElseThrow(IllegalArgumentException::new);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getKeyName();
    }
}
