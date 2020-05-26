package pl.seb.czech.ilegal.back.domain.act.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActSearchResultDto {
    private List<ActDto> foundIsapActs = new ArrayList<>();
    private Integer offset;
    private Integer count;
    private Integer numOfResults;
}
