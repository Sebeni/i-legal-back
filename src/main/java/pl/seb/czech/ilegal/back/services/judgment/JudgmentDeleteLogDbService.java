package pl.seb.czech.ilegal.back.services.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDeleteLog;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;
import pl.seb.czech.ilegal.back.repositories.act.ActDeleteLogRepository;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentDeleteLogRepository;
import pl.seb.czech.ilegal.back.services.DeleteLogDbService;

import java.util.List;
import java.util.function.Supplier;

@Service
public class JudgmentDeleteLogDbService extends DeleteLogDbService<JudgmentDeleteLog> {

    @Autowired
    public JudgmentDeleteLogDbService(JudgmentDeleteLogRepository deleteLogRepository) {
        super(deleteLogRepository, JudgmentDeleteLog::new);
    }
}
