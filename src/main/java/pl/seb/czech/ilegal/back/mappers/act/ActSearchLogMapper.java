package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

@Component
public class ActSearchLogMapper extends ListMapper<ActSearchLog, ActSearchLogDto> {
    
    @Override
    public ActSearchLog mapToEntity(ActSearchLogDto asld) {
        return new ActSearchLog(asld.getId(), asld.getCreatedOn(), asld.getResponseTime(), asld.getResultCount(),
                asld.getOnlyActInForce(), asld.getTitle(), asld.getKeyWord(), asld.getProperName(), asld.getPublisher(),
                asld.getYear(), asld.getPosition());
    }

    @Override
    public ActSearchLogDto mapToDto(ActSearchLog asl) {
        return new ActSearchLogDto(asl.getId(), asl.getCreatedOn(), asl.getResponseTime(), asl.getResultCount(),
                asl.getOnlyActInForce(), asl.getTitle(), asl.getKeyWord(), asl.getProperName(), asl.getPublisher(),
                asl.getYear(), asl.getPosition());
    }
}