package pl.seb.czech.ilegal.back.repositories.act;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ActSearchLogRepository extends CrudRepository<ActSearchLog, Long> {
}
