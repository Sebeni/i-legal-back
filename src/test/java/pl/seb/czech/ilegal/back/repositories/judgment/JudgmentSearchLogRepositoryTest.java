package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentSearchLogRepositoryTest extends RepositoryTest<JudgmentSearchLog, Long> {
    private static final LocalDateTime timeStamp = NowTime.generate();
  
    private static final Integer resultCount = 5;

    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();

        JudgmentSearchLog jsl = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(timeStamp, jsl.getCreatedOn()),
                () -> assertEquals(resultCount, jsl.getResultCount())
        );

        deleteEntityFromRepo();
    }
    
    @Autowired
    public JudgmentSearchLogRepositoryTest(JudgmentSearchLogRepository repository) {
        super(repository, new JudgmentSearchLog(null, timeStamp, "abc", resultCount));
    }
}