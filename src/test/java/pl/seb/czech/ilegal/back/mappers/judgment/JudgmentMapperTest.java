package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.*;
import pl.seb.czech.ilegal.back.domain.judgment.entity.*;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentMapperTest extends MapperTest<JudgmentSynopsis, JudgmentSynopsisDto> {

    static JudgmentSynopsis[] entityArray = {new JudgmentSynopsis(), new JudgmentSynopsis(), new JudgmentSynopsis(), new JudgmentSynopsis()};
    static JudgmentSynopsisDto[] dtoArray = {new JudgmentSynopsisDto(), new JudgmentSynopsisDto()};

    String title = "Ustawa z dnia x lutego x r.";
    Integer year = 1994;
    Integer volume = 24;
    Integer position = 83;
    String text = "text";
    
    Long id = 9L;
    Long saosId = 555L;
    CourtType courtType = CourtType.COMMON;
    Set<String> caseNumbers = new HashSet<>(Arrays.asList("I ABC/EF"));
    JudgmentType judgmentType = JudgmentType.SENTENCE;
    String customName = "custom name";
    String synopsis = "synopsis";
    LocalDate judgmentDate = LocalDate.of(2222,2,2);

    String textContent = "o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego";
    Set<ReferencedRegulationDto> referencedRegulationsDto = new HashSet<>(Arrays.asList(new ReferencedRegulationDto()));
    Set<ReferencedRegulation> referencedRegulations = new HashSet<>(Arrays.asList(new ReferencedRegulation()));
    Set<String> keywords = new HashSet<>(Arrays.asList("keyword"));
    Set<String> legalBases = new HashSet<>(Arrays.asList("legalBase"));
    
    private final JudgmentMapper judgmentMapper;
    
    @Autowired
    public JudgmentMapperTest(JudgmentMapper mapper) {
        super(entityArray, dtoArray, mapper);
        this.judgmentMapper = mapper;
    }

    @Test
    void mapToReferencedRegulation() {
        ReferencedRegulationDto refRegDto = new ReferencedRegulationDto(title, year, volume, position, text);
        ReferencedRegulation result = judgmentMapper.mapToReferencedRegulation(refRegDto);
        
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
        ReferencedRegulation refReg = new ReferencedRegulation(title, year, volume, position, text);
        ReferencedRegulationDto result = judgmentMapper.mapToReferencedRegulationDto(refReg);

        assertAll(
                () -> assertEquals(title, result.getTitle()),
                () -> assertEquals(year, result.getYear()),
                () -> assertEquals(volume, result.getVolume()),
                () -> assertEquals(position, result.getPosition()),
                () -> assertEquals(text, result.getText())
        );
    }
    
    @Test
    void mapToJudgmentDetails() {
        JudgmentDetailsDto jdd = new JudgmentDetailsDto(null, saosId, textContent, referencedRegulationsDto, keywords, legalBases);
        JudgmentDetails result = judgmentMapper.mapToJudgmentDetails(jdd);
        
        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(textContent, result.getTextContent()),
                () -> assertEquals(1, result.getReferencedRegulations().size()),
                () -> assertEquals(1, result.getKeywords().size()),
                () -> assertEquals(1, result.getLegalBases().size())
        );
    }

    @Test
    void mapToJudgmentDetailsDto() {
        JudgmentDetails jd = new JudgmentDetails(null, saosId, textContent, referencedRegulations, keywords, legalBases);
        JudgmentDetailsDto result = judgmentMapper.mapToJudgmentDetailsDto(jd);

        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(textContent, result.getTextContent()),
                () -> assertEquals(1, result.getReferencedRegulations().size()),
                () -> assertEquals(1, result.getKeywords().size()),
                () -> assertEquals(1, result.getLegalBases().size())
        );
    }

    @Test
    void mapToJudgmentSynopsis() {
        
        
        JudgmentDetailsDto jdd = new JudgmentDetailsDto(null, saosId, textContent, referencedRegulationsDto, keywords, legalBases);

        JudgmentSynopsisDto jsd = new JudgmentSynopsisDto(id, saosId, courtType, caseNumbers, judgmentType, customName, synopsis, judgmentDate, jdd);
        JudgmentSynopsis result = mapper.mapToEntity(jsd);
        
        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(courtType, result.getCourtType()),
                () -> assertEquals(judgmentType, result.getJudgmentType()),
                () -> assertEquals(customName, result.getCustomName()),
                () -> assertEquals(synopsis, result.getSynopsis()),
                () -> assertEquals(judgmentDate, result.getJudgmentDate()),
                () -> assertNull(result.getJudgmentDetails().getId()),
                () -> assertEquals(saosId, result.getJudgmentDetails().getSaosId()),
                () -> assertEquals(textContent, result.getJudgmentDetails().getTextContent()),
                () -> assertEquals(1, result.getJudgmentDetails().getReferencedRegulations().size()),
                () -> assertEquals(1, result.getJudgmentDetails().getKeywords().size()),
                () -> assertEquals(1, result.getJudgmentDetails().getLegalBases().size())
        );
    }

    @Test
    void mapToJudgmentSynopsisDto() {
        JudgmentDetails jd = new JudgmentDetails(id, saosId, textContent, referencedRegulations, keywords, legalBases);

        JudgmentSynopsis js = new JudgmentSynopsis(id, saosId, courtType, caseNumbers, judgmentType, customName, synopsis, judgmentDate, jd);
        JudgmentSynopsisDto result = mapper.mapToDto(js);

        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(saosId, result.getSaosId()),
                () -> assertEquals(courtType, result.getCourtType()),
                () -> assertEquals(judgmentType, result.getJudgmentType()),
                () -> assertEquals(customName, result.getCustomName()),
                () -> assertEquals(synopsis, result.getSynopsis()),
                () -> assertEquals(judgmentDate, result.getJudgmentDate()),
                () -> assertEquals(id, result.getJudgmentDetails().getId()),
                () -> assertEquals(saosId, result.getJudgmentDetails().getSaosId()),
                () -> assertEquals(textContent, result.getJudgmentDetails().getTextContent()),
                () -> assertEquals(1, result.getJudgmentDetails().getReferencedRegulations().size()),
                () -> assertEquals(1, result.getJudgmentDetails().getKeywords().size()),
                () -> assertEquals(1, result.getJudgmentDetails().getLegalBases().size())
        );
    }
}