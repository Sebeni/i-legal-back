package pl.seb.czech.ilegal.back.domain.judgment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteType;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity
public class JudgmentDeleteLog extends DeleteLog {
    public JudgmentDeleteLog(DeleteType deleteType, String sourceName, Long beforeDeleteElementCount) {
        super(deleteType, sourceName, beforeDeleteElementCount);
    }
}
