package pl.przemeknojman.util.restapi;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostRequest extends ApiRequest<Response> {
    public PostRequest(String baseUrl, String endpoint) {
        super(baseUrl, endpoint);
    }

    @Override
    public Response execute() {
        return (Response) given()
                .body(requestBody)
                .baseUri(baseUrl)
                .basePath(endpoint)
                .headers(headers)
                .queryParams(queryParams)
                .pathParams(pathParam)
                .filter(new RequestLoggingFilter())
                .when()
                .post()
                .then()
                .extract();
    }
}
