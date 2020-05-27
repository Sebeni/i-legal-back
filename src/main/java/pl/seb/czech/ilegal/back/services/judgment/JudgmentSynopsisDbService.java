package pl.seb.czech.ilegal.back.services.judgment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSynopsis;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentSynopsisRepository;
import pl.seb.czech.ilegal.back.services.DbService;
import pl.seb.czech.ilegal.back.services.ElementNotFound;

@Service
public class JudgmentSynopsisDbService extends DbService<JudgmentSynopsis, Long> {
    private JudgmentSynopsisRepository judgmentSynopsisRepository;
    
    @Autowired
    public JudgmentSynopsisDbService(JudgmentSynopsisRepository repository, JudgmentDeleteLogDbService deleteLogDbService) {
        super(repository, deleteLogDbService);
        this.judgmentSynopsisRepository = repository;
    }
    
    public JudgmentSynopsis findBySaosId(Long saosId) {
        return judgmentSynopsisRepository.findBySaosId(saosId).orElseThrow(() -> new ElementNotFound(saosId));
    }
}
