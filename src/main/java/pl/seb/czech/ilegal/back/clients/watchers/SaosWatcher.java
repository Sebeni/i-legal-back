package pl.seb.czech.ilegal.back.clients.watchers;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.SearchQuery;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsisSearchResult;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentSearchLogDbService;

@Component
@Aspect
public class SaosWatcher {
    private JudgmentSearchLog currentSearch = new JudgmentSearchLog();
    @Autowired
    private JudgmentSearchLogDbService dbService;

    @Before(value = "execution(* pl.seb.czech.ilegal.back.clients.judgment.SaosClient.performSearchQuery(..))" +
            "&& args(searchQuery)")
    public void saveSearchQuery(SearchQuery searchQuery){
        currentSearch = new JudgmentSearchLog();
        currentSearch.setSearchParams(searchQuery.getQueryParams().toString());
    }


    @AfterReturning(
            pointcut = "execution(* pl.seb.czech.ilegal.back.clients.judgment.SaosClient.performSearchQuery(..))",
            returning = "result"
    )
    public void saveResult(SaosJudgmentSynopsisSearchResult result){
        currentSearch.setResultCount(result.getNumOfResults());
        dbService.save(currentSearch);
    }
}
