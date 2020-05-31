package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchQueryDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActSearchQueryMapperTest {
    private String onlyActInForce = "ALL";
    private String title = "test";
    private String keyWord = "keyword";
    private String properName = "proper";
    private ActPublisher publisher = ActPublisher.WDU;
    private Integer year = 1;
    private Integer position = 2;
    private Integer offset = 0;

    @Autowired
    private ActSearchQueryMapper mapper;
    
    @Test
    void mapToQuery() {
        ActSearchQueryDto dto = new ActSearchQueryDto(0, onlyActInForce, title, keyWord, properName, publisher, year, position, offset);
        IsapActSearchQuery isap = mapper.mapToQuery(dto);
        
        assertAll(
                () -> assertEquals(dto.getOnlyActInForce(), isap.getOnlyActInForce()),
                () -> assertEquals(dto.getTitle(), isap.getTitle()),
                () -> assertEquals(dto.getKeyWord(), isap.getKeyWord()),
                () -> assertEquals(dto.getProperName(), isap.getProperName()),
                () -> assertEquals(dto.getPublisher(), isap.getPublisher()),
                () -> assertEquals(dto.getYear(), isap.getYear()),
                () -> assertEquals(dto.getPosition(), isap.getPosition()),
                () -> assertEquals(dto.getOffset(), isap.getOffset())
        );
    }
}