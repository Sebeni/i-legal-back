package pl.seb.czech.ilegal.back.clients.judgment;

import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.clients.URIGenerator;
import pl.seb.czech.ilegal.back.clients.SearchQuery;


import java.net.URI;

@Service
public class SaosURIGenerator extends URIGenerator {
    private final static String SAOS_API_URI = "https://www.saos.org.pl/api";
    private final static String SEARCH_ENDPOINT_URL = "/search/judgments";

    public SaosURIGenerator() {
        super(SAOS_API_URI);
    }


    public URI generateSearchQueryUri(SearchQuery query) {
        return super.generateSearchQueryUri(query, SEARCH_ENDPOINT_URL);
    }
}
