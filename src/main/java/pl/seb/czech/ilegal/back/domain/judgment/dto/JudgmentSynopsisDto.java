package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;

import java.time.LocalDate;
import java.util.Objects;
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
    @Setter
    private JudgmentDetailsDto judgmentDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgmentSynopsisDto that = (JudgmentSynopsisDto) o;
        return Objects.equals(id, that.id) &&
                saosId.equals(that.saosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saosId);
    }
}
