package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchQueryDto;

@Component
public class ActSearchQueryMapper {
    public IsapActSearchQuery mapToQuery(ActSearchQueryDto dto){
        return new IsapActSearchQuery(dto.getOnlyActInForce(), 
                dto.getTitle(), dto.getKeyWord(), dto.getProperName(), dto.getPublisher(), dto.getYear(),
                dto.getPosition(), dto.getOffset());
    }
}
