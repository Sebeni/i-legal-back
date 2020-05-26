package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ActSearchLogMapperTest extends MapperTest<ActSearchLog, ActSearchLogDto> {

    static ActSearchLog[] entityArray = {new ActSearchLog(), new ActSearchLog(), new ActSearchLog(), new ActSearchLog()};
    static ActSearchLogDto[] dtoArray = {new ActSearchLogDto(), new ActSearchLogDto()};
    
    @Autowired
    public ActSearchLogMapperTest(ActSearchLogMapper mapper) {
        super(entityArray, dtoArray, mapper);
    }

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

        ActSearchLogDto result = mapper.mapToDto(asl);

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

        ActSearchLog result = mapper.mapToEntity(asld);

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

//    @Test
//    void testMapToActSearchLogDtoFromQuery() {
//        String onlyActInForce = "wszystkie";
//        String title = "title";
//        String keyWord = "keyword";
//        String properName = "proper";
//        ActPublisher publisher = ActPublisher.WDU;
//        Integer year = 1999;
//        Integer position = 5;
//
//        IsapActSearchQuery query = new IsapActSearchQuery(onlyActInForce, title, keyWord, properName,
//                publisher, year, position, 0);
//
//
//        ActSearchLogDto result = mapper.mapToDto(query);
//
//        assertAll(
//                () -> assertEquals(onlyActInForce, result.getOnlyActInForce()),
//                () -> assertEquals(title, result.getTitle()),
//                () -> assertEquals(keyWord, result.getKeyWord()),
//                () -> assertEquals(properName, result.getProperName()),
//                () -> assertEquals(publisher, result.getPublisher()),
//                () -> assertEquals(year, result.getYear()),
//                () -> assertEquals(position, result.getPosition())
//        );
//    }

}
