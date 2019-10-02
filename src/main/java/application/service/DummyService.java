package application.service;

import application.model.enums.ExceptionType;

public interface DummyService {
    void produceException(ExceptionType type);
}
