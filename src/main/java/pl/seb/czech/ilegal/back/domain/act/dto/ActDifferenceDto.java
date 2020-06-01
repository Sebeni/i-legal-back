package pl.seb.czech.ilegal.back.domain.act.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.NowTime;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
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
