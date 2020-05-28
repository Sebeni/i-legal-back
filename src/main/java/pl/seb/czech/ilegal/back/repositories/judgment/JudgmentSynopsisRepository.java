package pl.seb.czech.ilegal.back.repositories.judgment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSynopsis;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface JudgmentSynopsisRepository extends CrudRepository<JudgmentSynopsis, Long> {
    
    Optional<JudgmentSynopsis> findBySaosId(Long saosId);
    
    boolean existsBySaosId(Long saosId);
}
