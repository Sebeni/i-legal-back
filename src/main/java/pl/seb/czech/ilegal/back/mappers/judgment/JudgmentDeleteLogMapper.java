package pl.seb.czech.ilegal.back.mappers.judgment;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;
import pl.seb.czech.ilegal.back.mappers.DeleteLogMapper;

@Component
public class JudgmentDeleteLogMapper extends DeleteLogMapper<JudgmentDeleteLog, JudgmentDeleteLogDto> {
    public JudgmentDeleteLogMapper() {
        super(JudgmentDeleteLogDto::new);
    }
}
