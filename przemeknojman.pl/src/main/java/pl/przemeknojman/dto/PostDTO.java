package pl.przemeknojman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PostDTO {

    private int id;
    private int userId;
    private String title;
    private String body;

    public PostDTO(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public PostDTO() {

    }

}
