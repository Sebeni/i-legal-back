package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.judgment.SaosJudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchQueryDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentSearchQueryMapperTest {
    @Autowired
    JudgmentSearchQueryMapper mapper;
    
    
    @Test
    void mapToQuery() {
        JudgmentSearchQueryDto dto = new JudgmentSearchQueryDto(20, CourtType.SUPREME, 
                "sign", 5, "ref", "ser", 5, "sort", "dir");
        SaosJudgmentSynopsisSearchQuery query = mapper.mapToQuery(dto);
        
        assertAll(
                () -> assertEquals(dto.getResultLimitPerPage(), query.getResultLimitPerPage()),
                () -> assertEquals(dto.getCourtType(), query.getCourtType()),
                () -> assertEquals(dto.getSignature(), query.getSignature()),
                () -> assertEquals(dto.getArticleNumber(), query.getArticleNumber()),
                () -> assertEquals(dto.getReferencedRegulationYearPos(), query.getReferencedRegulationYearPos()),
                () -> assertEquals(dto.getPageNumber(), query.getPageNumber()),
                () -> assertEquals(dto.getSortingField(), query.getSortingField()),
                () -> assertEquals(dto.getSortingDirection(), query.getSortingDirection())
        );
        
    }
}