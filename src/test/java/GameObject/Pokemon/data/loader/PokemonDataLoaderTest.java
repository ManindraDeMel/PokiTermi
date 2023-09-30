package GameObject.Pokemon.data.loader;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import GameObject.Pokemon.data.Type.Type;
import GameObject.Pokemon.data.Type.TypeDeserializers;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PokemonDataLoaderTest {

    private final ObjectMapper objectMapper;

    public PokemonDataLoaderTest() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(
                new SimpleModule()
                        .addDeserializer(Type.class, new TypeDeserializers.TypeJsonDeserializer())
                        .addKeyDeserializer(Type.class, new TypeDeserializers.TypeKeyDeserializer())
        );
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
    }

    @Test
    public void testTypeDeserialization() {
        String json = "\"Grass\"";

        try {
            Type type = objectMapper.readValue(json, Type.class);
            assertEquals(Type.GRASS, type);  // Expected value is GRASS
        } catch (IOException e) {
            fail("Deserialization failed: " + e.getMessage());
        }
    }

    @Test
    public void testTypeDeserializationWithLowerCase() {
        String json = "\"grass\"";  // lowercase value

        try {
            Type type = objectMapper.readValue(json, Type.class);
            assertEquals(Type.GRASS, type);  // Expected value is GRASS
        } catch (IOException e) {
            fail("Deserialization failed: " + e.getMessage());
        }
    }

    @Test
    public void testTypeDeserializationWithWhiteSpace() {
        String json = "\"  Grass  \"";  // value with leading/trailing whitespaces

        try {
            Type type = objectMapper.readValue(json, Type.class);
            assertEquals(Type.GRASS, type);  // Expected value is GRASS
        } catch (IOException e) {
            fail("Deserialization failed: " + e.getMessage());
        }
    }
}
