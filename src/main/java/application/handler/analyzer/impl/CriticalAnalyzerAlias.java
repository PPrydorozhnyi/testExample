package application.handler.analyzer.impl;

import application.exception.instance.CustomException;
import application.handler.analyzer.CriticalAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CriticalAnalyzerAlias implements CriticalAnalyzer {

    private Environment environment;

    @Autowired
    public CriticalAnalyzerAlias(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean isCritical(CustomException e) {
        boolean critical = Boolean.valueOf(environment.getProperty(e.getAlias()));

        System.out.println("Exception successfully classified: " + e.getMessage() +
                " with " + (critical ? "high" : "low") + " importance\n");

        return critical;
    }
}
