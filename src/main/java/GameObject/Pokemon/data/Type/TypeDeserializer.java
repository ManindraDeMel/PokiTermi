package GameObject.Pokemon.data.Type;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * @author Manindra de Mel
 */
public class TypeDeserializer extends KeyDeserializer {
    @Override
    public Type deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        return Type.valueOf(key.toUpperCase());
    }
}
