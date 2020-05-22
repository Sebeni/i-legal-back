package pl.seb.czech.ilegal.back.clients.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.back.clients.URIGenerator;
import pl.seb.czech.ilegal.back.clients.SearchQuery;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class IsapURIGenerator extends URIGenerator {
    private IsapActFilenameGenerator generator;
    private final String searchEndpointUrl = "/search";
    
    @Autowired
    public IsapURIGenerator(IsapActFilenameGenerator generator) {
        super("http://isap.sejm.gov.pl/api/isap");
        this.generator = generator;
    }
    
    public URI generateDownloadActURI(IsapAct isapActToDownload, IsapActTextType textType) {
        String downloadEndpointURL = apiUrl + "/deeds/{publisher}/{year}/{position}/text/{type}/{fileName}";
        Map<String, String> params = new HashMap<>();
        params.put("publisher", isapActToDownload.getPublisher().name());
        params.put("year", isapActToDownload.getYear().toString());
        params.put("position", isapActToDownload.getPosition().toString());
        params.put("type", textType.getSymbol());
        params.put("fileName", generator.generateTxtFilename(isapActToDownload, textType));

        return UriComponentsBuilder.fromUriString(downloadEndpointURL).buildAndExpand(params).toUri();
    }

 
    public URI generateSearchQueryUri(SearchQuery query) {
        return super.generateSearchQueryUri(query, searchEndpointUrl);
    }
}