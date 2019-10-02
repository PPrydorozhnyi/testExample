package util;

import org.springframework.boot.test.web.client.TestRestTemplate;

public class TestUtils {

    private static final TestRestTemplate restTemplate;

    static {
        restTemplate = new TestRestTemplate();
    }

    public static TestRestTemplate getTestRestTemplate() {
        return restTemplate;
    }

    public static String getBasicURL(int port, String uri) {
        return "http://localhost:" + port + uri;
    }
}
