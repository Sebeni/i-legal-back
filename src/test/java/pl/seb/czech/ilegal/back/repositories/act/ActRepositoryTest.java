package pl.seb.czech.ilegal.back.repositories.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActRepositoryTest extends RepositoryTest<Act, Long> {
    private static final String isapId = "-1";
    private static final ActPublisher ap = ActPublisher.WDU;
    private static final Integer year = 1964;
    private static final Integer volume = 16;
    private static final Integer position = 93;
    private static final String title = "Ustawa z dnia 23 kwietnia 1964 r. - Test.";
    private static final String status = "akt posiada tekst jednolity";
    private static final LocalDate promulgation = LocalDate.of(1964, 5, 18);
    private static final LocalDateTime changeDate = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
    private static final String publishUrl = "abc";
    private static final String unifiedUrl = "cde";
    
    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();
        Act actFromRepo = getEntityFromRepoById();

        assertAll(
                () -> assertEquals(isapId, actFromRepo.getIsapId()),
                () -> assertEquals(ap, actFromRepo.getPublisher()),
                () -> assertEquals(year, actFromRepo.getYear()),
                () -> assertEquals(volume, actFromRepo.getVolume()),
                () -> assertEquals(position, actFromRepo.getPosition()),
                () -> assertEquals(title, actFromRepo.getTitle()),
                () -> assertEquals(status, actFromRepo.getStatus()),
                () -> assertEquals(promulgation, actFromRepo.getPromulgationDate()),
                () -> assertEquals(changeDate, actFromRepo.getChangeDate()),
                () -> assertEquals(publishUrl, actFromRepo.getPublishedTextUrl()),
                () -> assertEquals(unifiedUrl, actFromRepo.getUnifiedTextUrl()),
                () -> assertEquals(initialEntityCount + 1, repository.count())
        );

        deleteEntityFromRepo();
    }

    @Test
    void shouldUpdateEntityAndNotSaveNew() {
        saveEntityInRepo();
        Long id = testedEntity.getId();

        LocalDateTime newChangeDate = LocalDateTime.of(2022, 2, 2, 2, 2, 2);
        String newStatus = "uchylony";
        Act changedAct = new Act(id, isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl);
        changedAct.setChangeDate(newChangeDate);
        changedAct.setStatus(newStatus);
        
        repository.save(changedAct);
        assertEquals(initialEntityCount + 1, repository.count());
        
        Act actFromRepo = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(newChangeDate, actFromRepo.getChangeDate()),
                () -> assertEquals(newStatus, actFromRepo.getStatus())
        );
        
        deleteEntityFromRepo();
    }

    @Autowired
    public ActRepositoryTest(ActRepository repository) {
        super(repository, new Act(null, isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl));
    }
}