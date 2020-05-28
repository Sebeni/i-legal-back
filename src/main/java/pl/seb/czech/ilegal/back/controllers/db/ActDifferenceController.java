package pl.seb.czech.ilegal.back.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.clients.act.ActDifferenceCheckerFacade;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.mappers.act.ActDifferenceMapper;
import pl.seb.czech.ilegal.back.services.act.ActDifferenceDbService;

import java.util.List;

@RestController
@RequestMapping(value = "${url.base}")
public class ActDifferenceController {
    @Autowired
    private ActDifferenceCheckerFacade actDifferenceCheckerFacade;
    @Autowired
    private ActDifferenceDbService dbService;
    @Autowired
    private ActDifferenceMapper actDifferenceMapper;
    
    @GetMapping(value = "${url.acts.difference.update}")
    public List<ActDifferenceDto> updateActs(){
        return actDifferenceCheckerFacade.getActDifferences();
    }
    
    @GetMapping(value = "${url.acts.difference}")
    public List<ActDifferenceDto> getDiffHistory(){
        return actDifferenceMapper.mapToDtoList(dbService.getAll());
    }
    
    @DeleteMapping(value = "${url.acts.difference}")
    public void deleteAllHistory(){
        dbService.deleteAll();
    }
}
