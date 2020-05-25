package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.repositories.act.ActDifferenceRepository;
import pl.seb.czech.ilegal.back.services.DbService;

@Service
public class ActDifferenceDbService extends DbService<ActDifference, Long> {
    
    @Autowired
    public ActDifferenceDbService(ActDifferenceRepository repository) {
        super(repository);
    }
}
