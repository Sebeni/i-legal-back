package pl.seb.czech.ilegal.back.clients.judgment.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaosReferencedRegulation {
    
    @JsonProperty(value = "journalTitle")
    private String title;

    @JsonProperty(value = "journalYear")
    private Integer year;

    @JsonProperty(value = "journalNo")
    private Integer volume;
    
    @JsonProperty(value = "journalEntry")
    private Integer position;
    
    private String text;
}
