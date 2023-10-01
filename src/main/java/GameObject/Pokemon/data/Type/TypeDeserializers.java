package GameObject.Pokemon.data.Type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * Contains custom deserializers for the 'Type' enum.
 * These deserializers help in converting JSON strings to 'Type' enum values.
 */
public class TypeDeserializers {

    /**
     * Custom deserializer for 'Type' enum.
     * Converts a JSON string value to its corresponding 'Type' enum value.
     */
    public static class TypeJsonDeserializer extends JsonDeserializer<Type> {
        @Override
        public Type deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            // Convert the JSON string to its corresponding 'Type' enum value
            return Type.valueOf(p.getText().trim().toUpperCase());
        }
    }

    /**
     * Custom key deserializer for 'Type' enum.
     * Converts a JSON key string to its corresponding 'Type' enum value.
     */
    public static class TypeKeyDeserializer extends KeyDeserializer {
        @Override
        public Type deserializeKey(String key, DeserializationContext ctxt) throws IOException {
            // Convert the JSON key string to its corresponding 'Type' enum value
            return Type.valueOf(key.trim().toUpperCase());
        }
    }
}
