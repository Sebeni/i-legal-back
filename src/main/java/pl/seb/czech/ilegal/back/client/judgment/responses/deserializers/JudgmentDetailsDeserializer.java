package pl.seb.czech.ilegal.back.client.judgment.responses.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import pl.seb.czech.ilegal.back.client.judgment.responses.SaosJudgmentDetails;
import pl.seb.czech.ilegal.back.client.judgment.responses.SaosReferencedRegulation;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JudgmentDetailsDeserializer extends JsonDeserializer<SaosJudgmentDetails> {

    @Override
    public SaosJudgmentDetails deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        JsonNode root = node.get("data");
        
        Long saosId = root.get("id").asLong();
        
        String textContent = root.get("textContent").asText();
        
        JsonNode refRegulationNode = root.get("referencedRegulations");
        SaosReferencedRegulation[] saosReferencedRegulations = codec.treeToValue(refRegulationNode, SaosReferencedRegulation[].class);
        Set<SaosReferencedRegulation> refRegSet = new HashSet<>(Arrays.asList(saosReferencedRegulations));
        
        JsonNode legalBasesNode = root.get("legalBases");
        String[] legalBases = codec.treeToValue(legalBasesNode, String[].class);
        Set<String> legalBasesSet = new HashSet<>(Arrays.asList(legalBases));
        
        
        JsonNode keywordsNode = root.get("keywords");
        String[] keywords = codec.treeToValue(keywordsNode, String[].class);
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
        
        return new SaosJudgmentDetails(saosId, textContent, refRegSet, legalBasesSet, keywordsSet);
    }
    
}
