package pl.seb.czech.ilegal.back.repositories.judgment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JudgmentSearchLogRepository extends CrudRepository<JudgmentSearchLog, Long> {
}
