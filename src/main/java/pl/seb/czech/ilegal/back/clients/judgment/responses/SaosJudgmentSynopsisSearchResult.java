package pl.seb.czech.ilegal.back.clients.judgment.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.SearchResult;
import pl.seb.czech.ilegal.back.clients.judgment.responses.deserializers.PageNumberDeserializer;
import pl.seb.czech.ilegal.back.clients.judgment.responses.deserializers.TotalResultsDeserializer;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaosJudgmentSynopsisSearchResult implements SearchResult<SaosJudgmentSynopsis> {
    
    @JsonProperty(value = "items")
    private List<SaosJudgmentSynopsis> results;
    
    @JsonProperty(value = "info")
    @JsonDeserialize(using = TotalResultsDeserializer.class)
    private Integer numOfResults;
    
    @JsonProperty(value = "queryTemplate")
    @JsonDeserialize(using = PageNumberDeserializer.class)
    private Integer pageNumber;

    @Override
    public Integer getNumOfResults() {
        return numOfResults;
    }

    @Override
    public List<SaosJudgmentSynopsis> getResultsList() {
        return results;
    }
}
