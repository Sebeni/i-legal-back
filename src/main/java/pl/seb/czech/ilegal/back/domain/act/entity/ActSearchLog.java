package pl.seb.czech.ilegal.back.domain.act.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.SearchLog;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    public ActSearchLog(Long id, LocalDateTime createdOn, Long responseTime, Integer resultCount,
                        String onlyActInForce, String title, String keyWord, String properName, 
                        ActPublisher publisher, Integer year, Integer position) {
        super(id, createdOn, responseTime, resultCount);
        this.onlyActInForce = onlyActInForce;
        this.title = title;
        this.keyWord = keyWord;
        this.properName = properName;
        this.publisher = publisher;
        this.year = year;
        this.position = position;
    }
}
