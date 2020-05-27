package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

@Component
public class ActSearchLogMapper extends ListMapper<ActSearchLog, ActSearchLogDto> {
    
    @Override
    public ActSearchLog mapToEntity(ActSearchLogDto asld) {
        return new ActSearchLog(asld.getId(), asld.getCreatedOn(), asld.getSearchParams(), asld.getResultCount());
    }

    @Override
    public ActSearchLogDto mapToDto(ActSearchLog asl) {
        return new ActSearchLogDto(asl.getId(), asl.getCreatedOn(), asl.getSearchParams(), asl.getResultCount());
    }
}