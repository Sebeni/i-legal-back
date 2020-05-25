package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentSearchLogRepositoryTest extends RepositoryTest<JudgmentSearchLog, Long> {
    private static LocalDateTime timeStamp = NowTime.generate();
    private static Long responseTime = 10L;
    private static CourtType courtType = CourtType.SUPREME;
    private static String signature = "I abc";
    private static Integer articleNumber = 5;
    private static String referencedRegulationYearPos = "100/10";
    private static String searchPhrase = "search";
    private static Integer resultCount = 5;

    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();

        JudgmentSearchLog jsl = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(timeStamp, jsl.getCreatedOn()),
                () -> assertEquals(responseTime, jsl.getResponseTime()),
                () -> assertEquals(courtType, jsl.getCourtType()),
                () -> assertEquals(signature, jsl.getSignature()),
                () -> assertEquals(articleNumber, jsl.getArticleNumber()),
                () -> assertEquals(referencedRegulationYearPos, jsl.getReferencedRegulationYearPos()),
                () -> assertEquals(searchPhrase, jsl.getSearchPhrase()),
                () -> assertEquals(resultCount, jsl.getResultCount())
        );

        deleteEntityFromRepo();
    }
    
    @Autowired
    public JudgmentSearchLogRepositoryTest(JudgmentSearchLogRepository repository) {
        super(repository, new JudgmentSearchLog(null, timeStamp, responseTime, resultCount, courtType, signature, articleNumber, referencedRegulationYearPos, searchPhrase));
    }
}