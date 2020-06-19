package pl.seb.czech.ilegal.back.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.back.clients.URIGenerator;
import pl.seb.czech.ilegal.back.clients.SearchQuery;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class IsapURIGenerator extends URIGenerator {
    private final IsapActFilenameGenerator generator;
    private final static String ISAP_API_URL = "http://isap.sejm.gov.pl/api/isap";
    private final static String SEARCH_ENDPOINT_URL = "/search";
    
    @Autowired
    public IsapURIGenerator(IsapActFilenameGenerator generator) {
        super(ISAP_API_URL);
        this.generator = generator;
    }
    
    public URI generateDownloadActURI(String isapId, IsapActTextType textType) {
        String downloadEndpointURL = apiUrl + "/deeds/{publisher}/{year}/{position}/text/{type}/{fileName}";
        Map<String, String> params = new HashMap<>();
        params.put("publisher", isapId.substring(0,3));
        params.put("year", isapId.substring(3, 7));
        params.put("position", isapId.substring(10));
        params.put("type", textType.getSymbol());
        params.put("fileName", generator.generateTxtFilename(isapId, textType));

        return UriComponentsBuilder.fromUriString(downloadEndpointURL).buildAndExpand(params).toUri();
    }

 
    public URI generateSearchQueryUri(SearchQuery query) {
        return super.generateSearchQueryUri(query, SEARCH_ENDPOINT_URL);
    }
}