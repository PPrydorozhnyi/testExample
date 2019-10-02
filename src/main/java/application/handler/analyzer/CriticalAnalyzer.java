package application.handler.analyzer;

import application.exception.instance.CustomException;

public interface CriticalAnalyzer {
    boolean isCritical(CustomException e);
}
