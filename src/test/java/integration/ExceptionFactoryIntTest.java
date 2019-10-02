package integration;

import application.Application;
import application.exception.factory.ExceptionFactory;
import application.exception.instance.CustomException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
public class ExceptionFactoryIntTest {

    private static final List<String> CRITICAL_EXCEPTIONS = Arrays.asList("connection", "server");

    @Autowired
    private ExceptionFactory exceptionFactory;


    @Test
    public void exceptionFactoryTest() {
        List<String> collect = exceptionFactory.getCriticalExceptions().stream()
                .map(CustomException::getAlias).collect(Collectors.toList());

        assertEquals(2, collect.size());
        assertEquals(CRITICAL_EXCEPTIONS, collect);

    }

}
