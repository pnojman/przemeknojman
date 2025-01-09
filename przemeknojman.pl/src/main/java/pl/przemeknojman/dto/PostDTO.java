package pl.przemeknojman.dto;

import lombok.Data;

@Data
public class PostDTO {

    private int id;
    private int userId;
    private String title;
    private String body;
}
