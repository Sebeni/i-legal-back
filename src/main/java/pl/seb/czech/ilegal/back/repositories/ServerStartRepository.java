package pl.seb.czech.ilegal.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.ServerStartLog;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ServerStartRepository extends CrudRepository<ServerStartLog, Long> {
}
