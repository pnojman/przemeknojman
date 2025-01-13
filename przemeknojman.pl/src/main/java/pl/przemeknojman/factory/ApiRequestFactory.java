package pl.przemeknojman.factory;

import pl.przemeknojman.dto.TestParametersDTO;
import pl.przemeknojman.util.restapi.*;

public class ApiRequestFactory {

    public static ApiRequest createRequest(String method, TestParametersDTO testParameters, String endpoint) {
        switch (method.toUpperCase()) {
            case "GET":
                return new GetRequest(testParameters, endpoint);
            case "POST":
                return new PostRequest(testParameters, endpoint);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }
}
