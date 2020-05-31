package pl.seb.czech.ilegal.back.mappers;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.ChangeViewLog;
import pl.seb.czech.ilegal.back.domain.ChangeViewLogDto;

@Component
public class ChangeViewLogMapper {
    public ChangeViewLog mapToEntity(ChangeViewLogDto dto){
        return new ChangeViewLog(dto.getId(), dto.getViewName(), dto.getTimestamp());
    }
    
    public ChangeViewLogDto mapToDto(ChangeViewLog entity) {
        return new ChangeViewLogDto(entity.getId(), entity.getViewName(), entity.getTimestamp());
    }
}
