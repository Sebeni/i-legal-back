package pl.seb.czech.ilegal.back.domain.judgment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class JudgmentSynopsis implements BaseEntity<Long> {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    private Long saosId;

    @Enumerated(EnumType.STRING)
    private CourtType courtType;

    @ElementCollection
    private Set<String> caseNumbers = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    private JudgmentType judgmentType;
    private String customName;
    private String synopsis;
    private LocalDate judgmentDate;

    @Setter
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "judgment_details_id")
    private JudgmentDetails judgmentDetails;
}
