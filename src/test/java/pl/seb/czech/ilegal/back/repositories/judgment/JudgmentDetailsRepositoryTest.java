package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;
import pl.seb.czech.ilegal.back.domain.judgment.entity.ReferencedRegulation;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentDetailsRepositoryTest extends RepositoryTest<JudgmentDetails, Long> {
    @Autowired
    ReferencedRegulationRepository refRegRepository;
    
    
    private static final Long saosId = -1L;
    private static final String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
    private static final Set<ReferencedRegulation> referencedRegulations = new HashSet<>();
    private static final Set<String> keywords = new HashSet<>();
    private static final Set<String> legalBases = new HashSet<>();
    
    @Test
    void shouldSaveAndGetEntityWithProperties(){
        saveEntityInRepo();
        
        JudgmentDetails entityFromRepo = getEntityFromRepoById();

        assertAll(
                () -> assertEquals(saosId, entityFromRepo.getSaosId()),
                () -> assertEquals(textContent, entityFromRepo.getTextContent()),
                () -> assertEquals(initialEntityCount + 1, repository.count())
        );
        
        deleteEntityFromRepo();
    }
    
    @Test
    void shouldSaveAndDeleteRefReg() {
        saveEntityInRepo();
        
        ReferencedRegulation refReg = new ReferencedRegulation("title", 1,2,3,"text");
        
        long refRegCountBefore = refRegRepository.count();
        
        JudgmentDetails jd = getEntityFromRepoById();
        jd.getReferencedRegulations().add(refReg);
        
        repository.save(jd);

        JudgmentDetails finalJd = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(refRegCountBefore + 1, refRegRepository.count()),
                () -> assertTrue(finalJd.getReferencedRegulations().contains(refReg))
        );
        
        repository.deleteById(jd.getId());

        assertAll(
                () -> assertEquals(initialEntityCount, repository.count()),
                () -> assertEquals(refRegCountBefore, refRegRepository.count())
        );
    }
    
    @Autowired
    public JudgmentDetailsRepositoryTest(JudgmentDetailsRepository repository) {
        super(repository, new JudgmentDetails(null, saosId, textContent, referencedRegulations, keywords, legalBases));
    }
}