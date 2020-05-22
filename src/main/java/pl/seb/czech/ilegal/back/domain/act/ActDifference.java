package pl.seb.czech.ilegal.back.domain.act;

import lombok.Getter;
import pl.seb.czech.ilegal.back.client.act.responses.IsapAct;

import java.time.LocalDateTime;

@Getter
public class ActDifference {
    private final String beforeUpdate = "PRZED AKTUALIZACJĄ";
    private final String afterUpdate = "PO AKTUALIZACJI";
    
    private String title;
    private String statusBefore;
    private String statusAfter;
    private LocalDateTime lastChangeBefore;
    private LocalDateTime lastChangeAfter;

    public ActDifference(IsapAct actBefore, IsapAct actAfter) {
        title = actBefore.getTitle();
        statusBefore = actBefore.getStatus();
        statusAfter = actAfter.getStatus();
        lastChangeBefore = actBefore.getChangeDate();
        lastChangeAfter = actAfter.getChangeDate();
    }
    
    public String getBeforeChangesText(){
        return generateText(beforeUpdate);
    }
    
    public String getAfterChangesText(){
        return generateText(afterUpdate);
        
    }
    
    private String generateText(String beforeOrAfter) {
        StringBuilder sb = new StringBuilder(beforeOrAfter)
                .append(" ost. zmiana: ")
                .append((beforeOrAfter.equals(beforeUpdate) ? lastChangeBefore : lastChangeAfter));

        if(statusesAreNotEqual()) {
            sb.append(" status: ").append((beforeOrAfter.equals(beforeUpdate) ? statusBefore : statusAfter));
        }
        return sb.toString();
        
    }
    
    private boolean statusesAreNotEqual(){
        return !statusBefore.equals(statusAfter);
    }
}
