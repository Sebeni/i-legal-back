package pl.seb.czech.ilegal.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.ElementNotFoundLog;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ElementNotFoundLogRepository extends CrudRepository<ElementNotFoundLog, Long> {
}
