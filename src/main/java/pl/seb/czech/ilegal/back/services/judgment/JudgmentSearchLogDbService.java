package pl.seb.czech.ilegal.back.services.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentSearchLogRepository;
import pl.seb.czech.ilegal.back.services.DbService;

@Service
public class JudgmentSearchLogDbService extends DbService<JudgmentSearchLog, Long> {

    @Autowired
    public JudgmentSearchLogDbService(JudgmentSearchLogRepository repository) {
        super(repository);
    }
}
