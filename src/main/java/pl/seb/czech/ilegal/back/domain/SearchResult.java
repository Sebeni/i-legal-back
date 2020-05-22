package pl.seb.czech.ilegal.back.domain;


import java.util.List;

public interface SearchResult<E> {
    Integer getNumOfResults();
    
    List<E> getResultsList();
    
}
