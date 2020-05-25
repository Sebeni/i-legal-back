package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

@Component
public class ActMapper extends ListMapper<Act, ActDto> {
    
    @Override
    public Act mapToEntity(ActDto ad) {
        return new Act(ad.getId(), ad.getIsapId(), ad.getPublisher(), ad.getYear(), ad.getVolume(),
                ad.getPosition(), ad.getTitle(), ad.getStatus(), ad.getPromulgationDate(), ad.getChangeDate(),
                ad.getPublishedTextUrl(), ad.getUnifiedTextUrl());
    }

    @Override
    public ActDto mapToDto(Act act) {
        return new ActDto(act.getId(), act.getIsapId(), act.getPublisher(), act.getYear(), act.getVolume(),
                act.getPosition(), act.getTitle(), act.getStatus(), act.getPromulgationDate(), act.getChangeDate(),
                act.getPublishedTextUrl(), act.getUnifiedTextUrl());
    }
}
