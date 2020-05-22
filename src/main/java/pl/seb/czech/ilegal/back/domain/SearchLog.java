package pl.seb.czech.ilegal.back.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@MappedSuperclass
public abstract class SearchLog implements BaseEntity<Long> {
    @Id
    @GeneratedValue
    @NotNull
    protected Long id;

    @Column(updatable = false)
    protected LocalDateTime timeStamp = LocalDateTime.now(ZoneId.of("GMT+2"));
    protected Long responseTime;
    
}
