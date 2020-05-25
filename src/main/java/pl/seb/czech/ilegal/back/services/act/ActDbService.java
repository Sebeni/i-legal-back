package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.repositories.act.ActRepository;
import pl.seb.czech.ilegal.back.services.DbService;

@Service
public class ActDbService extends DbService<Act, Long> {

    @Autowired
    public ActDbService(ActRepository actRepo) {
        super(actRepo);
    }
    
    
}
