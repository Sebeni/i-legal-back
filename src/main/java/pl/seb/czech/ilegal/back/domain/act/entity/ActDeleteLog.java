package pl.seb.czech.ilegal.back.domain.act.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteType;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ActDeleteLog extends DeleteLog {
    public ActDeleteLog(DeleteType deleteType, String sourceName, Long beforeDeleteElementCount) {
        super(deleteType, sourceName, beforeDeleteElementCount);
    }
}
