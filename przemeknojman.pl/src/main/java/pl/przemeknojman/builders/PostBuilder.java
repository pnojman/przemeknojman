package pl.przemeknojman.builders;

import com.github.javafaker.Faker;
import pl.przemeknojman.dto.PostDTO;

public class PostBuilder {

    private final Faker faker;
    private int id;
    private int userId;
    private String title;
    private String body;

    public PostBuilder() {
        this.faker = new Faker();
    }

    public PostBuilder withRandomUserId() {
        this.userId = faker.number().numberBetween(1,100);
        return this;
    }

    public PostBuilder withRandomTitle() {
        this.title = faker.name().title();
        return this;
    }

    public PostBuilder withRandomBody() {
        this.body = faker.name().title();
        return this;
    }

    public PostDTO build() {
        return new PostDTO(this.userId, this.title, this.body);
    }

}
