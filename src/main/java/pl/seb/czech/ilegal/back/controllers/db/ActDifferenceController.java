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
    
    @PutMapping(value = "${url.difference}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ActDifferenceDto> checkForUpdates(@RequestBody List<Long> actIds){
        return actDifferenceCheckerFacade.getActDifferences(actIds);
    }
    
    @GetMapping(value = "${url.difference}")
    public List<ActDifferenceDto> getAllDifferences(){
        return actDifferenceMapper.mapToDtoList(dbService.getAll());
    }
    
    @DeleteMapping(value = "${url.difference}")
    public void deleteAll(){
        dbService.deleteAll();
    }
}
