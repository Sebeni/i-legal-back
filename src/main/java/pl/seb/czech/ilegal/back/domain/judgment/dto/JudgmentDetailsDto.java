package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Set<String> legalBases;
    private Set<String> keywords;

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
