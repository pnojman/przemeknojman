package pl.przemeknojman.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class JsonUtils {

    public static String readJsonFromResource(String filePath) {
        ClassLoader classLoader = JsonUtils.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());
        try {
            return new String(Files.readAllBytes(Paths.get(file.getPath())));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <T> T parseJsonToObject(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }
}
