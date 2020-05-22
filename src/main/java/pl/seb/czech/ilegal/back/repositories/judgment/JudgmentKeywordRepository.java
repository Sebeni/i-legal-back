package pl.seb.czech.ilegal.back.repositories.judgment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentKeyword;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JudgmentKeywordRepository extends CrudRepository<JudgmentKeyword, String> {
}
