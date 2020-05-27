package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDeleteLog;
import pl.seb.czech.ilegal.back.repositories.act.ActDeleteLogRepository;
import pl.seb.czech.ilegal.back.services.DeleteLogDbService;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ActDeleteLogDbService extends DeleteLogDbService<ActDeleteLog> {

    @Autowired
    public ActDeleteLogDbService(ActDeleteLogRepository actDeleteLogRepository) {
        super(actDeleteLogRepository, ActDeleteLog::new);
    }
}
