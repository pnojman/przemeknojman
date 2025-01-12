package pl.przemeknojman.builders;

import com.github.javafaker.Faker;
import pl.przemeknojman.dto.JbnaActionLogsExtensionsDTO;

public class JbnaActionLogsExtensionsBuilder {

    private final Faker faker;
    private int id;
    private String extension;

    public JbnaActionLogsExtensionsBuilder(Faker faker) {

        this.faker = faker;
    }

    public JbnaActionLogsExtensionsBuilder withRandomId() {
        this.id = faker.number().numberBetween(1,100);
        return this;
    }

    public JbnaActionLogsExtensionsBuilder withRandomExtension() {
        this.extension = faker.name().title();
        return this;
    }

    public JbnaActionLogsExtensionsDTO build() {
        return new JbnaActionLogsExtensionsDTO(this.id, this.extension);
    }

}
