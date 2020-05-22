package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
public abstract class SearchLogDto {
    protected Long id;
    protected LocalDateTime createdOn;
    @Setter
    protected Long responseTime;
    @Setter
    protected Integer resultCount;
}
