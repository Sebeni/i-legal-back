package pl.seb.czech.ilegal.back.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.clients.act.ActDifferenceFinderFacade;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.mappers.act.ActDifferenceMapper;
import pl.seb.czech.ilegal.back.scheduler.ScheduledMessageDto;
import pl.seb.czech.ilegal.back.scheduler.UpdateScheduler;
import pl.seb.czech.ilegal.back.services.act.ActDifferenceDbService;

import java.util.List;

@RestController
@RequestMapping(value = "${url.base}")
public class ActDifferenceController {
    @Autowired
    private ActDifferenceFinderFacade actDifferenceFinderFacade;
    @Autowired
    private ActDifferenceDbService dbService;
    @Autowired
    private ActDifferenceMapper actDifferenceMapper;
    @Autowired
    private UpdateScheduler updateScheduler;
    
    @GetMapping(value = "${url.acts.difference.update}")
    public List<ActDifferenceDto> updateAndGetActs(){
        return actDifferenceFinderFacade.getActDifferences();
    }
    
    @GetMapping(value = "${url.acts.difference.scheduled}")
    public ScheduledMessageDto getScheduledInfo(){return updateScheduler.getScheduledMessageDto();}
    
    @GetMapping(value = "${url.acts.difference}")
    public List<ActDifferenceDto> getDiffHistory(){
        return actDifferenceMapper.mapToDtoList(dbService.getAll());
    }
    
    @DeleteMapping(value = "${url.acts.difference}")
    public void deleteAllHistory(){
        dbService.deleteAll();
    }
}
