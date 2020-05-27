package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

@Component
public class ActDifferenceMapper extends ListMapper<ActDifference, ActDifferenceDto> {
    
    @Override
    public ActDifference mapToEntity(ActDifferenceDto add) {
        return new ActDifference(add.getId(), add.getTitle(), add.getStatusBefore(), add.getStatusAfter(),
                add.getLastChangeBefore(), add.getLastChangeAfter(), add.getCreatedOn(), add.isDifferentAfter());
    }

    @Override
    public ActDifferenceDto mapToDto(ActDifference ad) {
        return new ActDifferenceDto(ad.getId(), ad.getTitle(), ad.getStatusBefore(), ad.getStatusAfter(),
                ad.getLastChangeBefore(), ad.getLastChangeAfter(), ad.getCreatedOn(), ad.isDifferentAfter());
    }
}
