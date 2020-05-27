package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentSearchLogMapperTest extends MapperTest<JudgmentSearchLog, JudgmentSearchLogDto> {

    static JudgmentSearchLog[] entityArray = {new JudgmentSearchLog(), new JudgmentSearchLog(), new JudgmentSearchLog(), new JudgmentSearchLog()};
    static JudgmentSearchLogDto[] dtoArray = {new JudgmentSearchLogDto(), new JudgmentSearchLogDto()};
    
    @Autowired
    public JudgmentSearchLogMapperTest(JudgmentSearchLogMapper mapper) {
        super(entityArray, dtoArray, mapper);
    }

    @Test
    void mapToJudgmentSearchLog() {
        Long id = -1L;
        LocalDateTime createdOn = NowTime.generate();
        String searchUrl = "searchUrl";
        Integer resultCount = 6;

        JudgmentSearchLogDto jsld = new JudgmentSearchLogDto(id, createdOn, searchUrl, resultCount);
        JudgmentSearchLog result = mapper.mapToEntity(jsld);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(resultCount, result.getResultCount()),
                () -> assertEquals(searchUrl, result.getSearchParams())
                
        );
    }

    @Test
    void mapToJudgmentSearchLogDto() {
        Long id = -1L;
        LocalDateTime createdOn = NowTime.generate();
        String searchUrl = "searchUrl";
        Integer resultCount = 6;

        JudgmentSearchLog jsl = new JudgmentSearchLog(id, createdOn, searchUrl, resultCount);
        JudgmentSearchLogDto result = mapper.mapToDto(jsl);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(createdOn, result.getCreatedOn()),
                () -> assertEquals(resultCount, result.getResultCount()),
                () -> assertEquals(searchUrl, result.getSearchParams())
        );
    }
}