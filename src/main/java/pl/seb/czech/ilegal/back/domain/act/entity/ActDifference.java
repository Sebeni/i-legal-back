package pl.seb.czech.ilegal.back.domain.act.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import pl.seb.czech.ilegal.back.domain.NowTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ActDifference implements BaseEntity<Long> {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;
    private String title;
    private String statusBefore;
    private String statusAfter;
    private LocalDateTime lastChangeBefore;
    private LocalDateTime lastChangeAfter;
    @Column(updatable = false)
    private LocalDateTime createdOn = NowTime.generate();
}
