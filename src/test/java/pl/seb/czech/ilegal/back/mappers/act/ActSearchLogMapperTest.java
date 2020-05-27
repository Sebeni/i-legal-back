package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
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
        Long id = -1L;
        LocalDateTime createdOn = NowTime.generate();
        String searchUrl = "searchUrl";
        Integer resultCount = 6;
      

        ActSearchLog asl = new ActSearchLog(id, createdOn,searchUrl, resultCount);

        ActSearchLogDto result = mapper.mapToDto(asl);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(resultCount, result.getResultCount()),
                () -> assertEquals(searchUrl, result.getSearchParams())
        );
    }

    @Test
    void mapToActSearchLog() {
        Long id = -1L;
        LocalDateTime createdOn = NowTime.generate();
        String searchUrl = "searchUrl";
        Integer resultCount = 6;
        
        ActSearchLogDto asld = new ActSearchLogDto(id, createdOn, searchUrl, resultCount);

        ActSearchLog result = mapper.mapToEntity(asld);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(resultCount, result.getResultCount()),
                () -> assertEquals(searchUrl, result.getSearchParams())
        );
    }
}
