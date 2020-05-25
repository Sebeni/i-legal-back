package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentKeyword;
import pl.seb.czech.ilegal.back.domain.judgment.entity.ReferencedRegulation;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentDetailsDto {
    private Long id;
    private Long saosId;
    private String textContent;
    private Set<ReferencedRegulationDto> referencedRegulations;
    private Set<JudgmentKeywordDto> keywords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgmentDetailsDto that = (JudgmentDetailsDto) o;
        return Objects.equals(id, that.id) &&
                saosId.equals(that.saosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saosId);
    }
}
