package pl.seb.czech.ilegal.back.domain.act.dto;

import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteLogDto;
import pl.seb.czech.ilegal.back.domain.DeleteType;

import java.time.LocalDateTime;

public class ActDeleteLogDto extends DeleteLogDto {
    public ActDeleteLogDto(Long id, LocalDateTime timeStamp, String sourceName, Long beforeDeleteElementCount, DeleteType deleteType) {
        super(id, timeStamp, sourceName, beforeDeleteElementCount, deleteType);
    }

    public ActDeleteLogDto() {
    }
}
