package pl.seb.czech.ilegal.back.clients;

import pl.seb.czech.ilegal.back.domain.SearchResult;

public interface Client<T> {
    
    SearchResult<T> performSearchQuery(SearchQuery searchQuery);
}
