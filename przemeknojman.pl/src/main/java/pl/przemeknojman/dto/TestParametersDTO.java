package pl.przemeknojman.dto;

import lombok.Data;

@Data
public class TestParametersDTO {

    private String url;
    private String apiUrl;
    private String browser;

    private String dbHost;
    private String dbName;
    private int dbPort;
    private String dbUserName;
    private String dbPassword;

}
