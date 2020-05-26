package pl.seb.czech.ilegal.back.clients;

public interface Client<T> {
    
    SearchResult<T> performSearchQuery(SearchQuery searchQuery);
}
