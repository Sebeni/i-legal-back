package pl.seb.czech.ilegal.back.repositories.judgment;

import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;

public interface JudgmentDeleteLogRepository extends CrudRepository<JudgmentDeleteLog, Long> {
}
