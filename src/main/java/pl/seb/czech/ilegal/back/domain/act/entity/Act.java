package pl.seb.czech.ilegal.back.domain.act.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Act implements BaseEntity<Long> {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;
    private String isapId;
    
    @Enumerated(EnumType.STRING)
    private ActPublisher publisher;
    private Integer year;
    private Integer volume;
    private Integer position;

    @Type(type = "text")
    private String title;
    
    @Setter
    private String status;
    private LocalDate promulgationDate;
    
    @Setter
    private LocalDateTime changeDate;
    private String publishedTextUrl;
    private String unifiedTextUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Act act = (Act) o;
        return isapId.equals(act.isapId) &&
                publisher == act.publisher &&
                Objects.equals(year, act.year) &&
                Objects.equals(position, act.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isapId, publisher, year, position);
    }
}
