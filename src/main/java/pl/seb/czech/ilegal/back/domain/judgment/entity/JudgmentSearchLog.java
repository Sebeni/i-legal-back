package pl.seb.czech.ilegal.back.domain.judgment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.back.domain.SearchLog;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class JudgmentSearchLog extends SearchLog {
    public JudgmentSearchLog(Long id, LocalDateTime createdOn, String searchUrl, Integer resultCount) {
        super(id, createdOn, searchUrl, resultCount);
    }
}
