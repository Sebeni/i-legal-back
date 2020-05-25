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
    private static LocalDateTime timeStamp = NowTime.generate();
    private static Long responseTime = 555L;
    private static String onlyActInForce = "Obowiązujące";
    private static String title = "title";
    private static String keyWord = "keyword";
    private static String properName = "proper name";
    private static ActPublisher publisher = ActPublisher.WDU;
    private static Integer year = 1;
    private static Integer position = 2;
    private static Integer resultCount = 3;

    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();

        ActSearchLog asl = getEntityFromRepoById();
        assertAll(
                () -> assertEquals(timeStamp, asl.getCreatedOn()),
                () -> assertEquals(responseTime, asl.getResponseTime()),
                () -> assertEquals(onlyActInForce, asl.getOnlyActInForce()),
                () -> assertEquals(title, asl.getTitle()),
                () -> assertEquals(keyWord, asl.getKeyWord()),
                () -> assertEquals(properName, asl.getProperName()),
                () -> assertEquals(publisher, asl.getPublisher()),
                () -> assertEquals(year, asl.getYear()),
                () -> assertEquals(position, asl.getPosition()),
                () -> assertEquals(resultCount, asl.getResultCount())
        );
        
        deleteEntityFromRepo();
    }
    
    
    @Autowired
    public ActSearchLogRepositoryTest(ActSearchLogRepository repository) {
        super(repository, new ActSearchLog(null, timeStamp, responseTime, resultCount, onlyActInForce, title, keyWord, properName, publisher, year, position));
    }
}