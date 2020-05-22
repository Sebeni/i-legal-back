package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSynopsis;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentSynopsisRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentSynopsisRepositoryTest extends RepositoryTest<JudgmentSynopsis, Long> {

    private static Long saosId = 555L;
    private static CourtType courtType = CourtType.COMMON;
    private static Set<String> caseNumbers = new HashSet<>();
    private static JudgmentType judgmentType = JudgmentType.SENTENCE;
    private static String customName = "custom name";
    private static String synopsis = "synopsis";
    private static LocalDate judgmentDate = LocalDate.of(2222,2,2);
    
    @Autowired
    JudgmentDetailsRepository detailsRepository;
    
    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();

        JudgmentSynopsis js = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(saosId, js.getSaosId()),
                () -> assertEquals(courtType, js.getCourtType()),
                () -> assertEquals(judgmentType, js.getJudgmentType()),
                () -> assertEquals(customName, js.getCustomName()),
                () -> assertEquals(synopsis, js.getSynopsis()),
                () -> assertEquals(judgmentDate, js.getJudgmentDate())
        );
        
        deleteEntityFromRepo();
    }
    
    @Test
    void shouldAddAndDeleteJudgmentDetails() {
        long detailRepositoryCountBefore = detailsRepository.count();
        JudgmentDetails jd = new JudgmentDetails();
        testedEntity.setJudgmentDetails(jd);
        saveEntityInRepo();
        
        assertAll(
                () -> assertEquals(detailRepositoryCountBefore + 1, detailsRepository.count()),
                () -> assertTrue(detailsRepository.existsById(jd.getId()))
        );
        
        deleteEntityFromRepo();
        
        assertAll(
                () -> assertEquals(detailRepositoryCountBefore, detailsRepository.count()),
                () -> assertFalse(detailsRepository.existsById(jd.getId()))
        );
        
    }
    
    

    @Autowired
    public JudgmentSynopsisRepositoryTest(JudgmentSynopsisRepository repository) {
        super(repository, new JudgmentSynopsis(null, saosId, courtType, caseNumbers, judgmentType, customName, synopsis, judgmentDate, null));
    }
}