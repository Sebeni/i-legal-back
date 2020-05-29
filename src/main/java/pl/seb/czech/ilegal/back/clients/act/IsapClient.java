package pl.seb.czech.ilegal.back.clients.act;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.back.clients.Client;
import pl.seb.czech.ilegal.back.clients.SearchQuery;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapActSearchResult;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@EnableAspectJAutoProxy
public class IsapClient implements Client<IsapAct> {
    private final IsapURIGenerator isapURIGenerator;
    private final RestTemplate restTemplate;
 

    @Autowired
    public IsapClient(IsapURIGenerator isapURIGenerator, RestTemplate restTemplate) {
        this.isapURIGenerator = isapURIGenerator;
        this.restTemplate = restTemplate;
    }

    public URI generateDownloadActURI(String isapId, IsapActTextType textType){
        return isapURIGenerator.generateDownloadActURI(isapId, textType);
    }
    
    public boolean validateTxtExists(String uri) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        HttpStatus responseStatus = null;
        try {
            ClientHttpRequest request = factory.createRequest(new URI(uri), HttpMethod.GET);
            responseStatus = request.execute().getStatusCode();
        } catch (IOException | URISyntaxException e) {
            log.error("Exception in check if text exists", e);
        }
        return responseStatus == HttpStatus.OK;
    }
    
    public List<String> getAllKeywordsAndNames() {
        String endpointURL = "/keywords";
        String[] result = restTemplate.getForObject(isapURIGenerator.getApiUrl() + endpointURL, String[].class);
        return result != null ? Arrays.asList(result) : new ArrayList<>();
    }
    
    @Override
    public IsapActSearchResult performSearchQuery(SearchQuery searchQuery) {
        URI requestUri = isapURIGenerator.generateSearchQueryUri(searchQuery);
        return restTemplate.getForObject(requestUri, IsapActSearchResult.class);
    }
}
