package pl.seb.czech.ilegal.back.repositories.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActDifferenceRepositoryTest extends RepositoryTest<ActDifference, Long> {
    private static String title = "title";
    private static String statusBefore = "obowiązujący";
    private static String statusAfter = "uchylony";
    private static LocalDateTime lastChangeBefore = LocalDateTime.of(1111,1,1,1,1,1);
    private static LocalDateTime lastChangeAfter = LocalDateTime.of(2222,2,2,2,2,2);
    private static LocalDateTime createdOn = NowTime.generate();
    private static String urlBefore = "abc";
    private static String urlAfter = "efg";
    
    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();
        
        ActDifference ad = getEntityFromRepoById();
        
        assertAll(
                () -> assertEquals(title, ad.getTitle()),
                () -> assertEquals(statusBefore, ad.getStatusBefore()),
                () -> assertEquals(statusAfter, ad.getStatusAfter()),
                () -> assertEquals(lastChangeBefore, ad.getLastChangeBefore()),
                () -> assertEquals(lastChangeAfter, ad.getLastChangeAfter()),
                () -> assertEquals(createdOn, ad.getCreatedOn()),
                () -> assertEquals(urlBefore, ad.getUnifiedTxtUrlBefore()),
                () -> assertEquals(urlAfter, ad.getUnifiedTxtUrlAfter())
        );
        
        deleteEntityFromRepo();
    }
    
    @Autowired
    public ActDifferenceRepositoryTest(ActDifferenceRepository repository) {
        super(repository, new ActDifference(null, title, statusBefore, statusAfter, lastChangeBefore, lastChangeAfter, createdOn, urlBefore, urlAfter));
    }
}