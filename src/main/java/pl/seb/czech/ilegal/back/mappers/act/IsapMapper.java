package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapActSearchResult;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.ResultQueryMapper;

@Component
public class IsapMapper extends ResultQueryMapper<IsapAct, ActDto> {
    
    public ActSearchResultDto mapToActSearchResultDto(IsapActSearchResult isapActSearchResult){
        return new ActSearchResultDto(mapToDtoList(isapActSearchResult.getFoundIsapActs()), 
                isapActSearchResult.getOffset(), isapActSearchResult.getCount(), isapActSearchResult.getNumOfResults());
    }
    
    @Override
    public ActDto mapToDto(IsapAct searchResultElement) {
        return new ActDto(null, 
                searchResultElement.getIsapId(),
                searchResultElement.getPublisher(),
                searchResultElement.getYear(),
                searchResultElement.getVolume(),
                searchResultElement.getPosition(),
                searchResultElement.getTitle(),
                searchResultElement.getStatus(),
                searchResultElement.getPromulgation(),
                searchResultElement.getChangeDate(),
                searchResultElement.getPublishedTextUrl(),
                searchResultElement.getUnifiedTextUrl());
    }
    
    public IsapAct mapToIsapAct(ActDto actDto){
        return new IsapAct(
                actDto.getIsapId(),
                actDto.getPublisher(),
                actDto.getYear(),
                actDto.getVolume(),
                actDto.getPosition(),
                actDto.getTitle(),
                actDto.getStatus(),
                actDto.getPromulgationDate(),
                actDto.getChangeDate(),
                actDto.getPublishedTextUrl(),
                actDto.getUnifiedTextUrl());
    }
}
