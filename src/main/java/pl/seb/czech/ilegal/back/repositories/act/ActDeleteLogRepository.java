package pl.seb.czech.ilegal.back.repositories.act;

import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDeleteLog;

public interface ActDeleteLogRepository extends CrudRepository<ActDeleteLog, Long> {
}
