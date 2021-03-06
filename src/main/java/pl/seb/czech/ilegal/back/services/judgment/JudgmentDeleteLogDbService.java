package pl.seb.czech.ilegal.back.services.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentDeleteLogRepository;
import pl.seb.czech.ilegal.back.services.DeleteLogDbService;

@Service
public class JudgmentDeleteLogDbService extends DeleteLogDbService<JudgmentDeleteLog> {

    @Autowired
    public JudgmentDeleteLogDbService(JudgmentDeleteLogRepository deleteLogRepository) {
        super(deleteLogRepository, JudgmentDeleteLog::new);
    }
}
