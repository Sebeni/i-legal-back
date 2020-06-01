package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.clients.act.IsapClient;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.repositories.act.ActKeywordRepository;
import pl.seb.czech.ilegal.back.services.DbService;

import java.util.List;

@Service
public class ActKeywordDbService extends DbService<ActKeyword, String> {
    private final IsapClient isapClient;
    
    @Autowired
    public ActKeywordDbService(ActKeywordRepository repository, ActDeleteLogDbService deleteLogDbService, 
                               IsapClient isapClient) {
        super(repository, deleteLogDbService);
        this.isapClient = isapClient;
    }
    
    public void saveAllKeywords(List<String> keywordStringList) {
        keywordStringList.forEach(s -> repository.save(new ActKeyword(s)));
    }

    @Override
    public List<ActKeyword> getAll() {
        if(repository.count() == 0){
            saveAllKeywords(isapClient.getAllKeywordsAndNames());
        }
        return super.getAll(); 
    }
}
