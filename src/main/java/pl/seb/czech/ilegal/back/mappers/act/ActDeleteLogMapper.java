package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDeleteLog;
import pl.seb.czech.ilegal.back.mappers.DeleteLogMapper;

@Component
public class ActDeleteLogMapper extends DeleteLogMapper<ActDeleteLog, ActDeleteLogDto> {
    public ActDeleteLogMapper() {
        super(ActDeleteLogDto::new);
    }
}
