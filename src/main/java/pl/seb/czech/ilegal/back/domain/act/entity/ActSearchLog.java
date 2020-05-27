package pl.seb.czech.ilegal.back.domain.act.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pl.seb.czech.ilegal.back.domain.SearchLog;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ActSearchLog extends SearchLog {
    public ActSearchLog(Long id, LocalDateTime createdOn, String searchUrl, Integer resultCount) {
        super(id, createdOn, searchUrl, resultCount);
    }
}
