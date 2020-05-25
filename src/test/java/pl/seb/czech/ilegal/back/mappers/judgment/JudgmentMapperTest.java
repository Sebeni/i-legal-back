package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActSearchLog;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.*;
import pl.seb.czech.ilegal.back.domain.judgment.entity.*;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentMapperTest extends MapperTest<JudgmentMapper, JudgmentSynopsis, JudgmentSynopsisDto> {

    static JudgmentSynopsis[] entityArray = {new JudgmentSynopsis(), new JudgmentSynopsis(), new JudgmentSynopsis(), new JudgmentSynopsis()};
    static JudgmentSynopsisDto[] dtoArray = {new JudgmentSynopsisDto(), new JudgmentSynopsisDto()};

    @Autowired
    public JudgmentMapperTest(JudgmentMapper mapper) {
        super(entityArray, dtoArray, mapper);
    }

    @Test
    void mapToReferencedRegulation() {
        String title = "Ustawa z dnia 4 lutego 1994 r. o prawie autorskim i prawach pokrewnych";
        Integer year = 1994;
        Integer volume = 24;
        Integer position = 83;
        String text = "Ustawa z dnia 4 lutego 1994 r. o prawie autorskim i prawach pokrewnych";

        ReferencedRegulationDto refRegDto = new ReferencedRegulationDto(title, year, volume, position, text);
        ReferencedRegulation result = mapper.mapToReferencedRegulation(refRegDto);
        
        assertAll(
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(volume, result.getVolume()),
                () -> assertEquals(position, result.getPosition()),
                () -> assertEquals(text, result.getText())
        );
    }

    @Test
    void mapToReferencedRegulationDto() {
        String title = "Ustawa z dnia 4 lutego 1994 r. o prawie autorskim i prawach pokrewnych";
        Integer year = 1994;
        Integer volume = 24;
        Integer position = 83;
        String text = "Ustawa z dnia 4 lutego 1994 r. o prawie autorskim i prawach pokrewnych";

        ReferencedRegulation refReg = new ReferencedRegulation(title, year, volume, position, text);
        ReferencedRegulationDto result = mapper.mapToReferencedRegulationDto(refReg);

        assertAll(
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(volume, result.getVolume()),
                () -> assertEquals(position, result.getPosition()),
                () -> assertEquals(text, result.getText())
        );
    }

    @Test
    void mapToJudgmentKeyword() {
        String name = "name";

        JudgmentKeywordDto jkd = new JudgmentKeywordDto(name);
        JudgmentKeyword result = mapper.mapToJudgmentKeyword(jkd);
        
        assertEquals(name, result.getName());
    }

    @Test
    void mapToJudgmentKeywordDto() {
        String name = "name";

        JudgmentKeyword jk = new JudgmentKeyword(name);
        JudgmentKeywordDto result = mapper.mapToJudgmentKeywordDto(jk);

        assertEquals(name, result.getName());
    }

    @Test
    void mapToJudgmentDetails() {
        Long saosId = 999L;
        String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
        Set<ReferencedRegulationDto> referencedRegulations = new HashSet<>();
        referencedRegulations.add(new ReferencedRegulationDto());
        Set<JudgmentKeywordDto> keywords = new HashSet<>();
        keywords.add(new JudgmentKeywordDto());

        JudgmentDetailsDto jdd = new JudgmentDetailsDto(null, saosId, textContent, referencedRegulations, keywords);
        JudgmentDetails result = mapper.mapToJudgmentDetails(jdd);
        
        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(textContent, result.getTextContent()),
                () -> assertEquals(1, result.getReferencedRegulations().size()),
                () -> assertEquals(1, result.getKeywords().size())
        );
    }

    @Test
    void mapToJudgmentDetailsDto() {
        Long saosId = 999L;
        String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
        Set<ReferencedRegulation> referencedRegulations = new HashSet<>();
        referencedRegulations.add(new ReferencedRegulation());
        Set<JudgmentKeyword> keywords = new HashSet<>();
        keywords.add(new JudgmentKeyword());

        JudgmentDetails jd = new JudgmentDetails(null, saosId, textContent, referencedRegulations, keywords);
        JudgmentDetailsDto result = mapper.mapToJudgmentDetailsDto(jd);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(textContent, result.getTextContent()),
                () -> assertEquals(1, result.getReferencedRegulations().size()),
                () -> assertEquals(1, result.getKeywords().size())
        );
    }

    @Test
    void mapToJudgmentSynopsis() {
        Long id = 9L;
        Long saosId = 555L;
        CourtType courtType = CourtType.COMMON;
        Set<String> caseNumbers = new HashSet<>();
        String caseNumber = "I ABC/EF"; 
        caseNumbers.add(caseNumber);
        JudgmentType judgmentType = JudgmentType.SENTENCE;
        String customName = "custom name";
        String synopsis = "synopsis";
        LocalDate judgmentDate = LocalDate.of(2222,2,2);
        
        String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
        Set<ReferencedRegulationDto> referencedRegulations = new HashSet<>();
        referencedRegulations.add(new ReferencedRegulationDto());
        Set<JudgmentKeywordDto> keywords = new HashSet<>();
        keywords.add(new JudgmentKeywordDto());
        JudgmentDetailsDto jdd = new JudgmentDetailsDto(null, saosId, textContent, referencedRegulations, keywords);

        JudgmentSynopsisDto jsd = new JudgmentSynopsisDto(id, saosId, courtType, caseNumbers, judgmentType, customName, synopsis, judgmentDate, jdd);
        JudgmentSynopsis result = mapper.mapToEntity(jsd);
        
        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(courtType, result.getCourtType()),
                () -> assertTrue(result.getCaseNumbers().contains(caseNumber)),
                () -> assertEquals(judgmentType, result.getJudgmentType()),
                () -> assertEquals(customName, result.getCustomName()),
                () -> assertEquals(synopsis, result.getSynopsis()),
                () -> assertEquals(judgmentDate, result.getJudgmentDate()),
                () -> assertNull(result.getJudgmentDetails().getId()),
                () -> assertEquals(saosId, result.getJudgmentDetails().getSaosId()),
                () -> assertEquals(textContent, result.getJudgmentDetails().getTextContent()),
                () -> assertEquals(1, result.getJudgmentDetails().getReferencedRegulations().size()),
                () -> assertEquals(1, result.getJudgmentDetails().getKeywords().size())
        );
    }

    @Test
    void mapToJudgmentSynopsisDto() {
        Long id = 9L;
        Long saosId = 555L;
        CourtType courtType = CourtType.COMMON;
        Set<String> caseNumbers = new HashSet<>();
        String caseNumber = "I ABC/EF";
        caseNumbers.add(caseNumber);
        JudgmentType judgmentType = JudgmentType.SENTENCE;
        String customName = "custom name";
        String synopsis = "synopsis";
        LocalDate judgmentDate = LocalDate.of(2222,2,2);
        
        String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
        Set<ReferencedRegulation> referencedRegulations = new HashSet<>();
        referencedRegulations.add(new ReferencedRegulation());
        Set<JudgmentKeyword> keywords = new HashSet<>();
        keywords.add(new JudgmentKeyword());
        JudgmentDetails jd = new JudgmentDetails(null, saosId, textContent, referencedRegulations, keywords);

        JudgmentSynopsis js = new JudgmentSynopsis(id, saosId, courtType, caseNumbers, judgmentType, customName, synopsis, judgmentDate, jd);
        JudgmentSynopsisDto result = mapper.mapToDto(js);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(courtType, result.getCourtType()),
                () -> assertTrue(result.getCaseNumbers().contains(caseNumber)),
                () -> assertEquals(judgmentType, result.getJudgmentType()),
                () -> assertEquals(customName, result.getCustomName()),
                () -> assertEquals(synopsis, result.getSynopsis()),
                () -> assertEquals(judgmentDate, result.getJudgmentDate()),
                () -> assertNull(result.getJudgmentDetails().getId()),
                () -> assertEquals(saosId, result.getJudgmentDetails().getSaosId()),
                () -> assertEquals(textContent, result.getJudgmentDetails().getTextContent()),
                () -> assertEquals(1, result.getJudgmentDetails().getReferencedRegulations().size()),
                () -> assertEquals(1, result.getJudgmentDetails().getKeywords().size())
        );
    }
}