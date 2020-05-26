package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentDetails;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsis;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsisSearchResult;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosReferencedRegulation;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.ReferencedRegulationDto;
import pl.seb.czech.ilegal.back.mappers.ResultQueryMapperTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaosMapperTest extends ResultQueryMapperTest<SaosJudgmentSynopsis, JudgmentSynopsisDto>{
    private static SaosJudgmentSynopsisSearchResult searchResult = new SaosJudgmentSynopsisSearchResult();
    static {
        searchResult.getResultsList().add(new SaosJudgmentSynopsis());
    }
    
    private static SaosMapper saosMapper;
    
    @Autowired
    public SaosMapperTest(SaosMapper mapper) {
        super(searchResult, mapper);
        saosMapper = mapper;
    }

    @Test
    void mapToDto() {
        SaosJudgmentSynopsis sjs = new SaosJudgmentSynopsis(5L, CourtType.SUPREME, null, 
                JudgmentType.DECISION, "custom", "synop", LocalDate.now());
        JudgmentSynopsisDto dto = mapper.mapToDto(sjs);
        
        assertAll(
                () -> assertEquals(sjs.getSaosId(), dto.getSaosId()),
                () -> assertEquals(sjs.getCourtType(), dto.getCourtType()),
                () -> assertEquals(sjs.getCaseNumbers(), dto.getCaseNumbers()),
                () -> assertEquals(sjs.getJudgmentType(), dto.getJudgmentType()),
                () -> assertEquals(sjs.getCustomName(), dto.getCustomName()),
                () -> assertEquals(sjs.getSynopsis(), dto.getSynopsis()),
                () -> assertEquals(sjs.getJudgmentDate(), dto.getJudgmentDate()),
                () -> assertNull(dto.getId()),
                () -> assertNull(dto.getJudgmentDetails())
        );
    }

    @Test
    void mapToJudgmentDetailDto() {
        SaosJudgmentDetails sjd = new SaosJudgmentDetails(123L, "context", null, null, null);
        JudgmentDetailsDto result = saosMapper.mapToJudgmentDetailDto(sjd);
        
        assertAll(
                () -> assertEquals(sjd.getSaosId(), result.getSaosId()),
                () -> assertEquals(sjd.getTextContent(), result.getTextContent()),
                () -> assertNull(result.getReferencedRegulations()),
                () -> assertEquals(sjd.getLegalBases(), result.getLegalBases()),
                () -> assertEquals(sjd.getKeywords(), result.getKeywords()),
                () -> assertNull(result.getId())
        );
    }

    @Test
    void mapToReferencedRegulationDto() {
        SaosReferencedRegulation srr = new SaosReferencedRegulation("title", 1, 2, 3, "text");
        ReferencedRegulationDto result = saosMapper.mapToReferencedRegulationDto(srr);
        
        assertAll(
                () -> assertEquals(srr.getTitle(), result.getTitle()),
                () -> assertEquals(srr.getYear(), result.getYear()),
                () -> assertEquals(srr.getVolume(), result.getVolume()),
                () -> assertEquals(srr.getPosition(), result.getPosition()),
                () -> assertEquals(srr.getText(), result.getText())
        );
    }

    @Test
    void mapToReferencedRegulationDtoSet() {
        Set<SaosReferencedRegulation> saosRefRegSet = new HashSet<>();
        saosRefRegSet.add(new SaosReferencedRegulation("title", 1, 2, 3, "text"));
        
        Set<ReferencedRegulationDto> result = saosMapper.mapToReferencedRegulationDtoSet(saosRefRegSet);
        
        assertAll(
                () -> assertEquals(1, result.size())
        );
    }
}