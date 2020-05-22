package pl.seb.czech.ilegal.back.domain.act.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import pl.seb.czech.ilegal.back.domain.SearchLog;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ActSearchLog extends SearchLog {
    private String onlyActInForce;
    private String title;
    private String keyWord;
    private String properName;
    @Enumerated(value = EnumType.STRING)
    private ActPublisher publisher;
    private Integer year;
    private Integer position;
    private Integer resultCount;
    
}
