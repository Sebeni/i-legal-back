package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.SearchResult;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapActSearchResult;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsis;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsisSearchResult;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.mappers.ResultQueryMapper;
import pl.seb.czech.ilegal.back.mappers.ResultQueryMapperTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapMapperTest extends ResultQueryMapperTest<IsapAct, ActDto> {
    private static IsapActSearchResult searchResult = new IsapActSearchResult();
    static {
        searchResult.getResultsList().add(new IsapAct());
    }

    @Autowired
    public IsapMapperTest(IsapMapper mapper) {
        super(searchResult, mapper);
    }

    @Test
    void mapToDto() {
        IsapAct isapAct = new IsapAct("1", ActPublisher.WDU, 1, 2, 3, "title",
                "status", LocalDate.now(), NowTime.generate(), "qwerty", "asdf");
        ActDto resutl = mapper.mapToDto(isapAct);
        
        assertAll(
                () -> assertEquals(isapAct.getIsapId(), resutl.getIsapId()),
                () -> assertEquals(isapAct.getPublisher(), resutl.getPublisher()),
                () -> assertEquals(isapAct.getYear(), resutl.getYear()),
                () -> assertEquals(isapAct.getVolume(), resutl.getVolume()),
                () -> assertEquals(isapAct.getPosition(), resutl.getPosition()),
                () -> assertEquals(isapAct.getTitle(), resutl.getTitle()),
                () -> assertEquals(isapAct.getStatus(), resutl.getStatus()),
                () -> assertEquals(isapAct.getPromulgation(), resutl.getPromulgationDate()),
                () -> assertEquals(isapAct.getChangeDate(), resutl.getChangeDate()),
                () -> assertEquals(isapAct.getPublishedTextUrl(), resutl.getPublishedTextUrl()),
                () -> assertEquals(isapAct.getUnifiedTextUrl(), resutl.getUnifiedTextUrl()),
                () -> assertNull(resutl.getId())
        );
    }
}