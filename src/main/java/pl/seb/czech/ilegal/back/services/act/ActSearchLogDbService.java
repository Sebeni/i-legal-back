package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.repositories.act.ActSearchLogRepository;
import pl.seb.czech.ilegal.back.services.DbService;

@Service
public class ActSearchLogDbService extends DbService<ActSearchLog, Long> {

    @Autowired
    public ActSearchLogDbService(ActSearchLogRepository repository, ActDeleteLogDbService deleteLogDbService) {
        super(repository, deleteLogDbService);
    }
}
