package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSynopsis;
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
        Long id = 123L;
        LocalDateTime timeStamp = NowTime.generate();
        Long responseTime = 10L;
        CourtType courtType = CourtType.SUPREME;
        String signature = "I abc";
        Integer articleNumber = 5;
        String referencedRegulationYearPos = "100/10";
        String searchPhrase = "search";
        Integer resultCount = 5;

        JudgmentSearchLogDto jsld = new JudgmentSearchLogDto(id, timeStamp, responseTime, resultCount, courtType, signature,
                articleNumber, referencedRegulationYearPos, searchPhrase);
        JudgmentSearchLog result = mapper.mapToEntity(jsld);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(timeStamp, result.getCreatedOn()),
                () -> assertEquals(responseTime, result.getResponseTime()),
                () -> assertEquals(courtType, result.getCourtType()),
                () -> assertEquals(signature, result.getSignature()),
                () -> assertEquals(articleNumber, result.getArticleNumber()),
                () -> assertEquals(referencedRegulationYearPos, result.getReferencedRegulationYearPos()),
                () -> assertEquals(searchPhrase, result.getSearchPhrase()),
                () -> assertEquals(resultCount, result.getResultCount())
        );
    }

    @Test
    void mapToJudgmentSearchLogDto() {
        Long id = 123L;
        LocalDateTime timeStamp = NowTime.generate();
        Long responseTime = 10L;
        CourtType courtType = CourtType.SUPREME;
        String signature = "I abc";
        Integer articleNumber = 5;
        String referencedRegulationYearPos = "100/10";
        String searchPhrase = "search";
        Integer resultCount = 5;

        JudgmentSearchLog jsl = new JudgmentSearchLog(id, timeStamp, responseTime, resultCount, courtType, signature,
                articleNumber, referencedRegulationYearPos, searchPhrase);
        JudgmentSearchLogDto result = mapper.mapToDto(jsl);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(timeStamp, result.getCreatedOn()),
                () -> assertEquals(responseTime, result.getResponseTime()),
                () -> assertEquals(courtType, result.getCourtType()),
                () -> assertEquals(signature, result.getSignature()),
                () -> assertEquals(articleNumber, result.getArticleNumber()),
                () -> assertEquals(referencedRegulationYearPos, result.getReferencedRegulationYearPos()),
                () -> assertEquals(searchPhrase, result.getSearchPhrase()),
                () -> assertEquals(resultCount, result.getResultCount())
        );
    }
}