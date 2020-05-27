package pl.seb.czech.ilegal.back.repositories.act;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ActRepository extends CrudRepository<Act, Long> {
    
    Optional<Act> findByIsapId(String isapId);
}
