package pl.przemeknojman.util.restapi;

import pl.przemeknojman.dto.TestParametersDTO;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiRequest<T> {

    protected String baseUrl;
    protected String endpoint;
    protected Map<String, String> headers = new HashMap<>();
    protected Map<String, String> queryParams = new HashMap<>();
    protected Map<String, Object> pathParam = new HashMap<>();
    protected String requestBody;

    public ApiRequest(TestParametersDTO testParameters, String endpoint) {
        this.baseUrl = testParameters.getApiUrl();
        this.endpoint = endpoint;
    }
    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }
    public void setQueryParam(String key, String value) {
        this.queryParams.put(key, value);
    }

    public void setPathParam(String key, Object value) {
        this.pathParam.put(key, value);
    }
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }


    public abstract T execute();
}
