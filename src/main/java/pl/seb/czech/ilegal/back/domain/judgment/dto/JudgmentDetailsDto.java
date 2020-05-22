package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentKeyword;
import pl.seb.czech.ilegal.back.domain.judgment.entity.ReferencedRegulation;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentDetailsDto {
    private Long id;
    private Long saosId;
    private String textContent;
    private Set<ReferencedRegulation> referencedRegulations;
    private Set<JudgmentKeyword> keywords;
}
