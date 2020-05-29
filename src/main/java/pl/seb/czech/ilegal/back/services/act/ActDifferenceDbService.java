package pl.seb.czech.ilegal.back.services.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.repositories.act.ActDifferenceRepository;
import pl.seb.czech.ilegal.back.services.DbService;

import java.util.List;

@Service
public class ActDifferenceDbService extends DbService<ActDifference, Long> {
    private final ActDifferenceRepository actDifferenceRepository;
    
    @Autowired
    public ActDifferenceDbService(ActDifferenceRepository repository, ActDeleteLogDbService deleteLogDbService) {
        super(repository, deleteLogDbService);
        this.actDifferenceRepository = repository;
    }
    
    public List<ActDifference> saveAll(List<ActDifference> entitiesToSave) {
        return (List<ActDifference>) actDifferenceRepository.saveAll(entitiesToSave);
    }
}
