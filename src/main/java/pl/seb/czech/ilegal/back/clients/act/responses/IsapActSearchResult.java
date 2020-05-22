package pl.seb.czech.ilegal.back.clients.act.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.back.domain.SearchResult;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsapActSearchResult implements SearchResult<IsapAct> {
    
    @JsonProperty("items")
    private List<IsapAct> foundIsapActs;
    
    private Integer offset;
    private Integer count;
    
    @JsonProperty("totalCount")
    private Integer numOfResults;
    
    @Override
    public List<IsapAct> getResultsList() {
        return foundIsapActs;
    }

    @Override
    public Integer getNumOfResults() {
        return numOfResults;
    }
}
