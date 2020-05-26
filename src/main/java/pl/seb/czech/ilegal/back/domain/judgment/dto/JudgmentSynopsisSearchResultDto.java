package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentSynopsisSearchResultDto {
    private List<JudgmentSynopsisDto> results = new ArrayList<>();
    private Integer numOfResults;
    private Integer pageNumber;
}
