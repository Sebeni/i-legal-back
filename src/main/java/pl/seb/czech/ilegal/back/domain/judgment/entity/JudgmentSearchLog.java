package pl.seb.czech.ilegal.back.domain.judgment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.SearchLog;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class JudgmentSearchLog extends SearchLog {
    @Enumerated(value = EnumType.STRING)
    private CourtType courtType;
    private String signature;
    private Integer articleNumber;
    private String referencedRegulationYearPos;
    private String searchPhrase;
    
    public JudgmentSearchLog(Long id, LocalDateTime createdOn, Long responseTime, Integer resultCount, 
                             CourtType courtType, String signature, Integer articleNumber, 
                             String referencedRegulationYearPos, String searchPhrase) {
        super(id, createdOn, responseTime, resultCount);
        this.courtType = courtType;
        this.signature = signature;
        this.articleNumber = articleNumber;
        this.referencedRegulationYearPos = referencedRegulationYearPos;
        this.searchPhrase = searchPhrase;
    }
}
