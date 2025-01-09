package pl.przemeknojman.factory;

import pl.przemeknojman.util.restapi.*;

public class ApiRequestFactory {

    public static ApiRequest createRequest(String method, String baseUrl, String endpoint) {
        switch (method.toUpperCase()) {
            case "GET":
                return new GetRequest(baseUrl, endpoint);
            case "POST":
                return new PostRequest(baseUrl, endpoint);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }
}
