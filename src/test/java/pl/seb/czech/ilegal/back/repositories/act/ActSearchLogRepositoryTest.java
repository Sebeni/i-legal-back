package pl.seb.czech.ilegal.back.repositories.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActSearchLogRepositoryTest extends RepositoryTest<ActSearchLog, Long> {
    private static final LocalDateTime timeStamp = NowTime.generate();
    private static final Integer resultCount = 3;

    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();

        ActSearchLog asl = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(timeStamp, asl.getCreatedOn()),
      
                () -> assertEquals(resultCount, asl.getResultCount())
        );
        
        deleteEntityFromRepo();
    }
    
    
    @Autowired
    public ActSearchLogRepositoryTest(ActSearchLogRepository repository) {
        super(repository, new ActSearchLog(null, timeStamp, "abc", resultCount));
    }
}