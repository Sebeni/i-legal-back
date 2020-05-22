package pl.seb.czech.ilegal.back.clients.judgment.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.clients.judgment.responses.deserializers.JudgmentDetailsDeserializer;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = JudgmentDetailsDeserializer.class)
public class SaosJudgmentDetails {
    @JsonProperty(value = "id")
    private Long saosId;
    private String textContent;
    private Set<SaosReferencedRegulation> saosReferencedRegulations;
    private Set<String> legalBases;
    private Set<String> keywords;
}
