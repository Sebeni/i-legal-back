package pl.seb.czech.ilegal.back.domain.act.dto;

import lombok.Getter;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class ActDifferenceDto {
    private Long id;
    private String title;
    private String statusBefore;
    private String statusAfter;
    private LocalDateTime lastChangeBefore;
    private LocalDateTime lastChangeAfter;
    private LocalDateTime createdOn;
    private String unifiedTxtUrlBefore;
    private String unifiedTxtUrlAfter;

    public ActDifferenceDto(Act actBefore, IsapAct actAfter) {
        title = actBefore.getTitle();
        statusBefore = actBefore.getStatus();
        statusAfter = actAfter.getStatus();
        lastChangeBefore = actBefore.getChangeDate();
        lastChangeAfter = actAfter.getChangeDate();
        unifiedTxtUrlBefore = actBefore.getUnifiedTextUrl();
        unifiedTxtUrlAfter = actAfter.getUnifiedTextUrl();
    }

    public ActDifferenceDto(Long id, String title, String statusBefore, String statusAfter, LocalDateTime lastChangeBefore, LocalDateTime lastChangeAfter, LocalDateTime createdOn, String unifiedTxtUrlBefore, String unifiedTxtUrlAfter) {
        this.id = id;
        this.title = title;
        this.statusBefore = statusBefore;
        this.statusAfter = statusAfter;
        this.lastChangeBefore = lastChangeBefore;
        this.lastChangeAfter = lastChangeAfter;
        this.createdOn = createdOn;
        this.unifiedTxtUrlBefore = unifiedTxtUrlBefore;
        this.unifiedTxtUrlAfter = unifiedTxtUrlAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActDifferenceDto that = (ActDifferenceDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn);
    }
}
