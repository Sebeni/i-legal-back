package pl.seb.czech.ilegal.back.domain.act.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class ActDifferenceDto {
    private Long id;
    private String title;
    private String statusBefore;
    private String statusAfter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChangeBefore;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastChangeAfter;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn = NowTime.generate();
    private boolean differentAfter;
    
    public ActDifferenceDto(ActDto actBefore, IsapAct actAfter) {
        title = actBefore.getTitle();
        statusBefore = actBefore.getStatus();
        statusAfter = actAfter.getStatus();
        lastChangeBefore = actBefore.getChangeDate();
        lastChangeAfter = actAfter.getChangeDate();
        
        differentAfter = !(statusBefore.equals(statusAfter)) || !(lastChangeBefore.equals(lastChangeAfter));
    }
    
    public ActDifferenceDto(Long id, String title, String statusBefore, String statusAfter, 
                            LocalDateTime lastChangeBefore, LocalDateTime lastChangeAfter, 
                            LocalDateTime createdOn, boolean differentAfter) {
        this.id = id;
        this.title = title;
        this.statusBefore = statusBefore;
        this.statusAfter = statusAfter;
        this.lastChangeBefore = lastChangeBefore;
        this.lastChangeAfter = lastChangeAfter;
        this.createdOn = createdOn;
        this.differentAfter = differentAfter;
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
