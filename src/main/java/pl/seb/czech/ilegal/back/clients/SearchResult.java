package pl.seb.czech.ilegal.back.clients;


import java.util.List;

public interface SearchResult<E> {
    Integer getNumOfResults();
    
    List<E> getResultsList();
    
}
