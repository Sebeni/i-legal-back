package pl.seb.czech.ilegal.back.domain.judgment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ReferencedRegulation implements BaseEntity<String> {
    @Id
    @NotNull
    private String title;

    private Integer year;
    private Integer volume;
    private Integer position;
    private String text;
    
    @Override
    public String getId() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferencedRegulation that = (ReferencedRegulation) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
