package pl.seb.czech.ilegal.back.domain.judgment.dto;

import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteLogDto;
import pl.seb.czech.ilegal.back.domain.DeleteType;

import java.time.LocalDateTime;

public class JudgmentDeleteLogDto  extends DeleteLogDto {
    public JudgmentDeleteLogDto(Long id, LocalDateTime timeStamp, String sourceName, Long beforeDeleteElementCount, DeleteType deleteType) {
        super(id, timeStamp, sourceName, beforeDeleteElementCount, deleteType);
    }

    public JudgmentDeleteLogDto() {
    }
}
