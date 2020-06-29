package pl.seb.czech.ilegal.back.scheduler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.ActDifferenceFinderFacade;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class UpdateScheduler {
    @Autowired
    private ActDifferenceFinderFacade diffFinder;

    public ScheduledMessageDto getScheduledMessageDto() {
        return scheduledMessageDto;
    }

    private ScheduledMessageDto scheduledMessageDto;
    
    @Scheduled(cron = "0 45 * * * *")
    public void updateActs() {
        List<ActDifferenceDto> updateResult = diffFinder.getActDifferences();
        log.info("Scheduled update");
        scheduledMessageDto = new ScheduledMessageDto(NowTime.generate(), updateResult);
    }
    
    @PostConstruct
    private void firstUpdate(){
        updateActs();
    }
}
