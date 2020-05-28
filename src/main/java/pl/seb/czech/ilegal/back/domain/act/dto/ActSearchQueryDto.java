package pl.seb.czech.ilegal.back.domain.act.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActSearchQueryDto {
    private Integer resultLimitPerPage;
    private String onlyActInForce;
    private String title;
    private String keyWord;
    private String properName;
    private ActPublisher publisher;
    private Integer year;
    private Integer position;
    private Integer offset = 0;
}
