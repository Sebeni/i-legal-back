package pl.seb.czech.ilegal.back.domain.judgment.entity;

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
@Entity
@Getter
public class JudgmentKeyword implements BaseEntity<String> {
    @Id
    @NotNull
    private String name;

    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JudgmentKeyword that = (JudgmentKeyword) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
