package pl.seb.czech.ilegal.back.mappers;

import org.junit.jupiter.api.Test;
import pl.seb.czech.ilegal.back.clients.SearchResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ResultQueryMapperTest<E, EDto> {
    SearchResult<E> searchResult;
    protected ResultQueryMapper<E, EDto> mapper;


    public ResultQueryMapperTest(SearchResult<E> searchResult, ResultQueryMapper<E, EDto> mapper) {
        this.searchResult = searchResult;
        this.mapper = mapper;
    }

    @Test
    void mapToDtoList() {
        List<EDto> result = mapper.mapToDtoList(searchResult.getResultsList());
        assertEquals(searchResult.getResultsList().size(), result.size());
    }
    
}