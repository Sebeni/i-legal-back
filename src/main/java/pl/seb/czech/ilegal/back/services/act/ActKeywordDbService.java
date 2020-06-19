package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.clients.act.IsapClient;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.repositories.act.ActKeywordRepository;
import pl.seb.czech.ilegal.back.services.DbService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActKeywordDbService extends DbService<ActKeyword, String> {
    private final IsapClient isapClient;
    @Value("${spring.profiles.active}")
    private String activeProfile;
    
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
        if(!activeProfile.equalsIgnoreCase("heroku")){
            if(repository.count() == 0){
                saveAllKeywords(isapClient.getAllKeywordsAndNames());
            }
            return super.getAll();
        } else {
            return isapClient.getAllKeywordsAndNames().stream()
                    .map(ActKeyword::new)
                    .collect(Collectors.toList());
        }
    }
}
