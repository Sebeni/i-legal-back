package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class SearchLog implements BaseEntity<Long> {
    @Id
    @GeneratedValue
    @NotNull
    protected Long id;

    @Column(updatable = false)
    protected LocalDateTime createdOn = NowTime.generate();

    @Type(type = "text")
    protected String searchParams;
    protected Integer resultCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchLog)) return false;
        SearchLog searchLog = (SearchLog) o;
        return Objects.equals(id, searchLog.id) &&
                Objects.equals(createdOn, searchLog.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn);
    }
}
