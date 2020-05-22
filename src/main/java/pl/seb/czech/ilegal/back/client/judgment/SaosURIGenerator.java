package pl.seb.czech.ilegal.back.client.judgment;

import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.client.URIGenerator;
import pl.seb.czech.ilegal.back.client.SearchQuery;


import java.net.URI;

@Service
public class SaosURIGenerator extends URIGenerator {
    private final String searchEndpointUrl = "/search/judgments";

    public SaosURIGenerator() {
        super("https://www.saos.org.pl/api");
    }


    public URI generateSearchQueryUri(SearchQuery query) {
        return super.generateSearchQueryUri(query, searchEndpointUrl);
    }
}
