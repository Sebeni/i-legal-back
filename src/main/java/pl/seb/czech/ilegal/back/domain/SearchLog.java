package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@MappedSuperclass
public abstract class SearchLog implements BaseEntity<Long> {
    @Id
    @GeneratedValue
    @NotNull
    protected Long id;

    @Column(updatable = false)
    protected LocalDateTime createdOn = NowTime.generate();
    
    protected Long responseTime;
    private Integer resultCount;

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
