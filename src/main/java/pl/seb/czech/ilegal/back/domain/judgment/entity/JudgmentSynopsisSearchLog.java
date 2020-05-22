package pl.seb.czech.ilegal.back.domain.judgment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import pl.seb.czech.ilegal.back.domain.SearchLog;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class JudgmentSynopsisSearchLog extends SearchLog {
    @Enumerated(value = EnumType.STRING)
    private CourtType courtType;
    private String signature;
    private Integer articleNumber;
    private String referencedRegulationYearPos;
    private String searchPhrase;
    private Integer resultCount;
}
