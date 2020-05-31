package pl.seb.czech.ilegal.back.repositories.judgment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JudgmentDeleteLogRepository extends CrudRepository<JudgmentDeleteLog, Long> {
}
