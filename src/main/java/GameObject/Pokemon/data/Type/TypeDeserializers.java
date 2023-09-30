package GameObject.Pokemon.data.Type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class TypeDeserializers {

    public static class TypeJsonDeserializer extends JsonDeserializer<Type> {
        @Override
        public Type deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Type.valueOf(p.getText().trim().toUpperCase());
        }
    }

    public static class TypeKeyDeserializer extends KeyDeserializer {
        @Override
        public Type deserializeKey(String key, DeserializationContext ctxt) throws IOException {
            return Type.valueOf(key.trim().toUpperCase());
        }
    }
}
