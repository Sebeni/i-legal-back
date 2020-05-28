package pl.seb.czech.ilegal.back.domain.judgment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgmentSearchQueryDto {
    private Integer resultLimitPerPage;
    private CourtType courtType;
    private String signature;
    private Integer articleNumber;
    private String referencedRegulationYearPos;
    private String searchPhrase;

    private Integer pageNumber;
    private String sortingField;
    private String sortingDirection;
}
