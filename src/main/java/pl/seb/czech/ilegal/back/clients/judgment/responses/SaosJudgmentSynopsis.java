package pl.seb.czech.ilegal.back.clients.judgment.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.clients.judgment.responses.deserializers.CaseNumberDeserializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaosJudgmentSynopsis {
    @JsonProperty(value = "id")
    private Long saosId;
    private CourtType courtType;

    @JsonProperty(value = "courtCases")
    @JsonDeserialize(using = CaseNumberDeserializer.class)
    private Set<String> caseNumbers;
    
    private JudgmentType judgmentType;
    private String customName;
    
    @JsonProperty(value = "textContent")
    private String synopsis;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate judgmentDate;
}
