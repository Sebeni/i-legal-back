package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDifference;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActDifferenceMapperTest extends MapperTest<ActDifference, ActDifferenceDto> {
    
    static ActDifference[] entityArray = {new ActDifference()};
    static ActDifferenceDto[] dtoArray = {new ActDifferenceDto(), new ActDifferenceDto()};
    
    @Autowired
    public ActDifferenceMapperTest(ActDifferenceMapper mapper) {
        super(entityArray, dtoArray, mapper);
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
        ActDifference result = mapper.mapToEntity(add);

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
        ActDifferenceDto result = mapper.mapToDto(ad);

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
}