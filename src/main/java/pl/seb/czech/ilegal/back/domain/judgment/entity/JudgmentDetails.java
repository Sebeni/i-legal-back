package pl.seb.czech.ilegal.back.domain.judgment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity
public class JudgmentDetails implements BaseEntity<Long> {
    
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    private Long saosId;
    private String textContent;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = ReferencedRegulation.class
    )
    @JoinColumn(name = "judg_det_id")
    private Set<ReferencedRegulation> referencedRegulations = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER,
            targetEntity = JudgmentKeyword.class
    )
    @JoinTable(
            name = "judgments_keywords",
            joinColumns = @JoinColumn(name = "judgment_detail_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword")
    )
    private Set<JudgmentKeyword> keywords = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgmentDetails that = (JudgmentDetails) o;
        return saosId.equals(that.saosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saosId);
    }
}
