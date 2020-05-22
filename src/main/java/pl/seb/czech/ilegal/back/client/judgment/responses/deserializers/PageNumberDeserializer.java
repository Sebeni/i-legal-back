package pl.seb.czech.ilegal.back.client.judgment.responses.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PageNumberDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return node.get("pageNumber").get("value").asInt();
    }
}
