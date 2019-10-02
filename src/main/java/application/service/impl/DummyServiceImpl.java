package application.service.impl;

import application.exception.instance.*;
import application.model.enums.ExceptionType;
import application.service.DummyService;
import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService {
    @Override
    public void produceException(ExceptionType type) {
        switch (type) {
            case BUSINESS:
                throw new BusinessValidationException();
            case MODEL:
                throw new ModelValidationException();
            case SERVER:
                throw new ServerException();
            case CONNECTION:
                throw new ConnectionException();
            default:
                throw new CustomException("Custom", false, "custom");
        }
    }
}
