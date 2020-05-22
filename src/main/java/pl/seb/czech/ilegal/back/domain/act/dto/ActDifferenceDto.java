package pl.seb.czech.ilegal.back.domain.act.dto;

import lombok.Getter;
import pl.seb.czech.ilegal.back.client.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class ActDifferenceDto {
    private Long id;
    private String title;
    private String statusBefore;
    private String statusAfter;
    private LocalDateTime lastChangeBefore;
    private LocalDateTime lastChangeAfter;
    private LocalDateTime createdOn;

    public ActDifferenceDto(Act actBefore, IsapAct actAfter) {
        title = actBefore.getTitle();
        statusBefore = actBefore.getStatus();
        statusAfter = actAfter.getStatus();
        lastChangeBefore = actBefore.getChangeDate();
        lastChangeAfter = actAfter.getChangeDate();
    }

    public ActDifferenceDto(Long id, String title, String statusBefore, String statusAfter, LocalDateTime lastChangeBefore, LocalDateTime lastChangeAfter, LocalDateTime createdOn) {
        this.id = id;
        this.title = title;
        this.statusBefore = statusBefore;
        this.statusAfter = statusAfter;
        this.lastChangeBefore = lastChangeBefore;
        this.lastChangeAfter = lastChangeAfter;
        this.createdOn = createdOn;
    }
}
