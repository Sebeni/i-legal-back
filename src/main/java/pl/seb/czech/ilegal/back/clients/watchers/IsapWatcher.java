package pl.seb.czech.ilegal.back.clients.watchers;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.SearchQuery;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapActSearchResult;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.services.act.ActSearchLogDbService;

@Aspect
@Component
public class IsapWatcher {
    private ActSearchLog currentSearch = new ActSearchLog();
    @Autowired
    private ActSearchLogDbService dbService;    
    
    @Before(value = "execution(* pl.seb.czech.ilegal.back.clients.act.IsapClient.performSearchQuery(..))" +
            "&& args(searchQuery)")
    public void saveSearchQuery(SearchQuery searchQuery){
        currentSearch = new ActSearchLog();
        currentSearch.setSearchParams(searchQuery.getQueryParams().toString());
    }
    
    
    @AfterReturning(
            pointcut = "execution(* pl.seb.czech.ilegal.back.clients.act.IsapClient.performSearchQuery(..))",
            returning = "result"
    )
    public void saveResult(IsapActSearchResult result){
        currentSearch.setResultCount(result.getNumOfResults());
        dbService.save(currentSearch);
    }
}
