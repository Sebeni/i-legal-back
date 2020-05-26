package pl.seb.czech.ilegal.back.mappers;

import pl.seb.czech.ilegal.back.clients.SearchResult;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ResultQueryMapper<E, EDto> {
    
    public abstract EDto mapToDto(E searchResultElement);
    
    public List<EDto> mapToDtoList(List<E> resultsList){
        return resultsList.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
