package pl.seb.czech.ilegal.back.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActKeywordDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActMapperTest {
    @Autowired
    ActMapper mapper;

    @Test
    void mapToActSearchLogDto() {
        Long id = 5L;
        LocalDateTime createdOn = NowTime.generate();
        Long responseTime = 100L;
        Integer resultCount = 1;
        String onlyActInForce = "wszystkie";
        String title = "title";
        String keyWord = "keyword";
        String properName = "proper";
        ActPublisher publisher = ActPublisher.WDU;
        Integer year = 1999;
        Integer position = 5;
        
        ActSearchLog asl = new ActSearchLog(id, createdOn, responseTime, resultCount, onlyActInForce, title,
                keyWord, properName, publisher, year, position);

        ActSearchLogDto result = mapper.mapToActSearchLogDto(asl);
        
        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(responseTime, result.getResponseTime()),
                () -> assertEquals(resultCount, result.getResultCount()),
                () -> assertEquals(onlyActInForce, result.getOnlyActInForce()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(keyWord, result.getKeyWord()),
                () -> assertEquals(properName, result.getProperName()),
                () -> assertEquals(publisher, result.getPublisher()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(position, result.getPosition())
        );
    }

    @Test
    void mapToActSearchLog() {
        Long id = 5L;
        LocalDateTime createdOn = NowTime.generate();
        Long responseTime = 100L;
        Integer resultCount = 1;
        String onlyActInForce = "wszystkie";
        String title = "title";
        String keyWord = "keyword";
        String properName = "proper";
        ActPublisher publisher = ActPublisher.WDU;
        Integer year = 1999;
        Integer position = 5;

        ActSearchLogDto asld = new ActSearchLogDto(id, createdOn, responseTime, resultCount, onlyActInForce, title,
                keyWord, properName, publisher, year, position);

        ActSearchLog result = mapper.mapToActSearchLog(asld);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(responseTime, result.getResponseTime()),
                () -> assertEquals(resultCount, result.getResultCount()),
                () -> assertEquals(onlyActInForce, result.getOnlyActInForce()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(keyWord, result.getKeyWord()),
                () -> assertEquals(properName, result.getProperName()),
                () -> assertEquals(publisher, result.getPublisher()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(position, result.getPosition())
        );
    }

    @Test
    void testMapToActSearchLogDtoFromQuery() {
        String onlyActInForce = "wszystkie";
        String title = "title";
        String keyWord = "keyword";
        String properName = "proper";
        ActPublisher publisher = ActPublisher.WDU;
        Integer year = 1999;
        Integer position = 5;
        
        IsapActSearchQuery query = new IsapActSearchQuery(onlyActInForce, title, keyWord, properName, 
                publisher, year, position, 0);


        ActSearchLogDto result = mapper.mapToActSearchLogDto(query);

        assertAll(
                () -> assertEquals(onlyActInForce, result.getOnlyActInForce()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(keyWord, result.getKeyWord()),
                () -> assertEquals(properName, result.getProperName()),
                () -> assertEquals(publisher, result.getPublisher()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(position, result.getPosition())
        );
    }

    @Test
    void mapToActKeyword() {
        String name = "name";

        ActKeywordDto akd = new ActKeywordDto(name);
        
        ActKeyword result = mapper.mapToActKeyword(akd);
        
        assertEquals(name, result.getName());
    }

    @Test
    void mapToActKeywordDto() {
        String name = "name";

        ActKeyword ak = new ActKeyword(name);

        ActKeywordDto result = mapper.mapToActKeywordDto(ak);

        assertEquals(name, result.getName());
    }

    @Test
    void mapToActDifference() {
        String title = "title";
        String statusBefore = "obowiązujący";
        String statusAfter = "uchylony";
        LocalDateTime lastChangeBefore = LocalDateTime.of(1111,1,1,1,1,1);
        LocalDateTime lastChangeAfter = LocalDateTime.of(2222,2,2,2,2,2);
        LocalDateTime createdOn = NowTime.generate();
        String unifiedTxtUrlBefore = "abc";
        String unifiedTxtUrlAfter = "def";
        
        ActDifferenceDto add = new ActDifferenceDto(null, title, statusBefore, statusAfter, lastChangeBefore, lastChangeAfter, createdOn, unifiedTxtUrlBefore, unifiedTxtUrlAfter);
        ActDifference result = mapper.mapToActDifference(add);
        
        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(statusBefore, result.getStatusBefore()),
                () -> assertEquals(statusAfter, result.getStatusAfter()),
                () -> assertEquals(lastChangeBefore, result.getLastChangeBefore()),
                () -> assertEquals(lastChangeAfter, result.getLastChangeAfter()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(unifiedTxtUrlBefore, result.getUnifiedTxtUrlBefore()),
                () -> assertEquals(unifiedTxtUrlAfter, result.getUnifiedTxtUrlAfter())
        );
    }

    @Test
    void mapToActDifferenceDto() {
        String title = "title";
        String statusBefore = "obowiązujący";
        String statusAfter = "uchylony";
        LocalDateTime lastChangeBefore = LocalDateTime.of(1111,1,1,1,1,1);
        LocalDateTime lastChangeAfter = LocalDateTime.of(2222,2,2,2,2,2);
        LocalDateTime createdOn = NowTime.generate();
        String unifiedTxtUrlBefore = "abc";
        String unifiedTxtUrlAfter = "def";

        ActDifference ad = new ActDifference(null, title, statusBefore, statusAfter, lastChangeBefore, lastChangeAfter, createdOn, unifiedTxtUrlBefore, unifiedTxtUrlAfter);
        ActDifferenceDto result = mapper.mapToActDifferenceDto(ad);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(statusBefore, result.getStatusBefore()),
                () -> assertEquals(statusAfter, result.getStatusAfter()),
                () -> assertEquals(lastChangeBefore, result.getLastChangeBefore()),
                () -> assertEquals(lastChangeAfter, result.getLastChangeAfter()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(unifiedTxtUrlBefore, result.getUnifiedTxtUrlBefore()),
                () -> assertEquals(unifiedTxtUrlAfter, result.getUnifiedTxtUrlAfter())
        );
    }

    @Test
    void mapToAct() {
        String isapId = "1";
        ActPublisher ap = ActPublisher.WDU;
        Integer year = 1964;
        Integer volume = 16;
        Integer position = 93;
        String title = "Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny.";
        String status = "akt posiada tekst jednolity";
        LocalDate promulgation = LocalDate.of(1964, 5, 18);
        LocalDateTime changeDate = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
        String publishUrl = "abc";
        String unifiedUrl = "cde";
        
        ActDto ad = new ActDto(null, isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl);
        Act result = mapper.mapToAct(ad);
        
        assertAll(
               () -> assertNull(result.getId()),
               () -> assertEquals(isapId, result.getIsapId()),
               () -> assertEquals(ap, result.getPublisher()),
               () -> assertEquals(year, result.getYear()),
               () -> assertEquals(volume, result.getVolume()),
               () -> assertEquals(position, result.getPosition()),
               () -> assertEquals(title, result.getTitle()),
               () -> assertEquals(status, result.getStatus()),
               () -> assertEquals(promulgation, result.getPromulgationDate()),
               () -> assertEquals(changeDate, result.getChangeDate()),
               () -> assertEquals(publishUrl, result.getPublishedTextUrl()),
               () -> assertEquals(unifiedUrl, result.getUnifiedTextUrl())
        );
    }

    @Test
    void mapToActDto() {
        String isapId = "1";
        ActPublisher ap = ActPublisher.WDU;
        Integer year = 1964;
        Integer volume = 16;
        Integer position = 93;
        String title = "Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny.";
        String status = "akt posiada tekst jednolity";
        LocalDate promulgation = LocalDate.of(1964, 5, 18);
        LocalDateTime changeDate = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
        String publishUrl = "abc";
        String unifiedUrl = "cde";

        Act act = new Act(null, isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl);
        ActDto result = mapper.mapToActDto(act);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(isapId, result.getIsapId()),
                () -> assertEquals(ap, result.getPublisher()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(volume, result.getVolume()),
                () -> assertEquals(position, result.getPosition()),
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(status, result.getStatus()),
                () -> assertEquals(promulgation, result.getPromulgationDate()),
                () -> assertEquals(changeDate, result.getChangeDate()),
                () -> assertEquals(publishUrl, result.getPublishedTextUrl()),
                () -> assertEquals(unifiedUrl, result.getUnifiedTextUrl())
        );
    }
}