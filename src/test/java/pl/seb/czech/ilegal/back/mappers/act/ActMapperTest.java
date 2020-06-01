package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActMapperTest extends MapperTest<Act, ActDto> {

    static Act[] entityArray = {new Act(), new Act(), new Act(), new Act()};
    static ActDto[] dtoArray = {new ActDto(), new ActDto()};

    String isapId = "1";
    ActPublisher ap = ActPublisher.WDU;
    Integer year = 4;
    Integer volume = -1;
    Integer position = -5;
    String title = "test";
    String status = "akt posiada test jednolity";
    LocalDate promulgation = LocalDate.of(1123, 5, 18);
    LocalDateTime changeDate = LocalDateTime.of(1234, 1, 1, 1, 1, 1);
    String publishUrl = "abc";
    String unifiedUrl = "cde";
    
    @Autowired
    public ActMapperTest(ActMapper mapper) {
        super(entityArray, dtoArray, mapper);
    }

    @Test
    void mapToAct() {
        ActDto ad = new ActDto(null, isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl);
        Act result = mapper.mapToEntity(ad);
        
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
        Act act = new Act(null, isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl);
        ActDto result = mapper.mapToDto(act);

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