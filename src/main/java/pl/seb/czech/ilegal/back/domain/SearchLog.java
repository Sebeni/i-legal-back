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
    protected LocalDateTime timeStamp = NowTime.generate();
    protected Long responseTime;
    
}
