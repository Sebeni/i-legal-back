package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentKeyword;
import pl.seb.czech.ilegal.back.domain.judgment.entity.ReferencedRegulation;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentDetailsRepository;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentKeywordRepository;
import pl.seb.czech.ilegal.back.repositories.judgment.ReferencedRegulationRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JudgmentDetailsRepositoryTest extends RepositoryTest<JudgmentDetails, Long> {
    @Autowired
    ReferencedRegulationRepository refRegRepository;
    @Autowired
    JudgmentKeywordRepository keywordRepository;
    
    private static Long saosId = 999L;
    private static String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
    private static Set<ReferencedRegulation> referencedRegulations = new HashSet<>();
    private static Set<JudgmentKeyword> keywords = new HashSet<>();
    
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
    
    @Test
    void shouldSaveKeywordAndNOTDeleteIt() {
        saveEntityInRepo();
        
        long keywordCountBefore = keywordRepository.count();
        String name = "name";
        JudgmentKeyword keyword = new JudgmentKeyword(name);
        
        JudgmentDetails jd = getEntityFromRepoById();
        jd.getKeywords().add(keyword);
        
        repository.save(jd);
       
        assertAll(
                () -> assertEquals(keywordCountBefore + 1, keywordRepository.count()),
                () -> assertEquals(1, jd.getKeywords().size())
        );
        
        repository.deleteById(jd.getId());
        
        assertAll(
                () -> assertEquals(keywordCountBefore + 1, keywordRepository.count()),
                () -> assertTrue(keywordRepository.existsById(keyword.getId())),
                () -> assertFalse(repository.existsById(jd.getId()))
        );
        
        keywordRepository.deleteById(keyword.getId());
        assertEquals(keywordCountBefore, keywordRepository.count());
    }
    
    @Autowired
    public JudgmentDetailsRepositoryTest(JudgmentDetailsRepository repository) {
        super(repository, new JudgmentDetails(null, saosId, textContent, referencedRegulations, keywords));
    }
}