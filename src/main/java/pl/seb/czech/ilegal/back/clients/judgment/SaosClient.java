package pl.seb.czech.ilegal.back.clients.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.seb.czech.ilegal.back.clients.Client;
import pl.seb.czech.ilegal.back.clients.SearchQuery;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentDetails;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsis;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsisSearchResult;

import java.net.URI;

@Component
@EnableAspectJAutoProxy
public class SaosClient implements Client<SaosJudgmentSynopsis> {
    private final RestTemplate restTemplate;
    private final SaosURIGenerator saosURIGenerator;

    @Autowired
    public SaosClient(RestTemplate restTemplate, SaosURIGenerator saosURIGenerator) {
        this.restTemplate = restTemplate;
        this.saosURIGenerator = saosURIGenerator;
    }
    
    @Override
    public SaosJudgmentSynopsisSearchResult performSearchQuery(SearchQuery searchQuery) {
        URI searchUri = saosURIGenerator.generateSearchQueryUri(searchQuery);
        return restTemplate.getForObject(searchUri, SaosJudgmentSynopsisSearchResult.class);
    }
    
    public SaosJudgmentDetails getJudgmentDetails(String saosId){ 
        String searchURL = saosURIGenerator.getApiUrl() + "/judgments/" + saosId;
        return restTemplate.getForObject(searchURL, SaosJudgmentDetails.class);
    }
}
