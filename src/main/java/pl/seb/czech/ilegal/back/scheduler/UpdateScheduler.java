package pl.seb.czech.ilegal.back.scheduler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.ActDifferenceFinderFacade;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;

import java.util.List;

@Component
@Slf4j
public class UpdateScheduler {
    @Autowired
    private ActDifferenceFinderFacade diffFinder;
    
    @Getter
    private ScheduledMessageDto scheduledMessageDto = new ScheduledMessageDto();
    
    @Scheduled(cron = "0 0 1 * * *")
    public void updateActs() {
        List<ActDifferenceDto> updateResult = diffFinder.getActDifferences();
        log.info("Scheduled update");
        scheduledMessageDto = new ScheduledMessageDto(NowTime.generate(), updateResult);
    }
}
