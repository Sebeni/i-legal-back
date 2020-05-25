package pl.seb.czech.ilegal.back.services.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSynopsis;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentSynopsisRepository;
import pl.seb.czech.ilegal.back.services.DbService;

@Service
public class JudgmentSynopsisDbService extends DbService<JudgmentSynopsis, Long> {

    @Autowired
    public JudgmentSynopsisDbService(JudgmentSynopsisRepository repository) {
        super(repository);
    }
}
