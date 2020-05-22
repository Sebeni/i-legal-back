package pl.seb.czech.ilegal.back.client.judgment.responses.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CaseNumberDeserializer extends JsonDeserializer<Set<String>> {

    @Override
    public Set<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        List<String> allCaseNumbers = new ArrayList<>();
        node.forEach(jsonNode -> allCaseNumbers.add(jsonNode.get("caseNumber").asText()));
        
        return new HashSet<>(allCaseNumbers);
    }
}
