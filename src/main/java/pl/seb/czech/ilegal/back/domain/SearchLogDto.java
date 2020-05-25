package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class SearchLogDto {
    protected Long id;
    protected LocalDateTime createdOn;
    @Setter
    protected Long responseTime;
    @Setter
    protected Integer resultCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchLogDto)) return false;
        SearchLogDto that = (SearchLogDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn);
    }
}
