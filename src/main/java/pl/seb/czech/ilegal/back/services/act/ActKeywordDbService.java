package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.repositories.act.ActKeywordRepository;
import pl.seb.czech.ilegal.back.services.DbService;

import java.util.List;
import java.util.Set;

@Service
public class ActKeywordDbService extends DbService<ActKeyword, String> {

    @Autowired
    public ActKeywordDbService(ActKeywordRepository repository) {
        super(repository);
    }
    
    public void saveAllKeywords(Set<ActKeyword> keywordSet) {
       repository.saveAll(keywordSet);
    }
}
