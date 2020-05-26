package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

import java.util.List;

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
    
    public ActDto mapIsapActToDto(IsapAct isapAct) {
        return new ActDto(null, isapAct.getIsapId(), isapAct.getPublisher(), isapAct.getYear(), isapAct.getVolume(),
                isapAct.getPosition(), isapAct.getTitle(), isapAct.getStatus(), isapAct.getPromulgation(), 
                isapAct.getChangeDate(), isapAct.getPublishedTextUrl(), isapAct.getUnifiedTextUrl());
    }
    
    
}
