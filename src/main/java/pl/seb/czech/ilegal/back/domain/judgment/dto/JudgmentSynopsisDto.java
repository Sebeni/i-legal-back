package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentSynopsisDto {
    private Long id;
    private Long saosId;
    private CourtType courtType;
    private Set<String> caseNumbers;
    private JudgmentType judgmentType;
    private String customName;
    private String synopsis;
    private LocalDate judgmentDate;
    private JudgmentDetails judgmentDetails;
}
