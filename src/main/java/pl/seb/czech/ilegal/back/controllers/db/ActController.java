package pl.seb.czech.ilegal.back.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActKeywordDto;
import pl.seb.czech.ilegal.back.mappers.act.ActKeywordMapper;
import pl.seb.czech.ilegal.back.mappers.act.ActMapper;
import pl.seb.czech.ilegal.back.services.act.ActDbService;
import pl.seb.czech.ilegal.back.services.act.ActKeywordDbService;

import java.util.List;

@RestController
@RequestMapping(value = "${url.base.db}")
public class ActController {
    @Autowired
    private ActDbService actDbService;
    @Autowired
    private ActKeywordDbService keywordDbService;
    @Autowired
    private ActKeywordMapper keywordMapper;
    @Autowired
    private ActMapper actMapper;
    
    @GetMapping(value = "${url.acts}")
    public List<ActDto> getAllEntities() {
        return actMapper.mapToDtoList(actDbService.getAll());
    }
    
    @GetMapping(value = "${url.acts}" + "/{entityId}")
    public ActDto getEntityById(@PathVariable Long entityId) {
        return actMapper.mapToDto(actDbService.getById(entityId));
    }
    
    @GetMapping(value = "${url.acts.byIsapId}" + "/{apiId}")
    public boolean checkEntityByApiId(@PathVariable String apiId) {
        return actDbService.existsByIsapId(apiId);
    }
    
    @PostMapping(value = "${url.acts}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActDto saveEntity(@RequestBody ActDto actDto) {
        return actMapper.mapToDto(actDbService.save(actMapper.mapToEntity(actDto)));
    }
    
    @PutMapping(value = "${url.acts}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActDto updateEntity(@RequestBody ActDto actDto) {
        return actMapper.mapToDto(actDbService.update(actMapper.mapToEntity(actDto)));
    }
    
    @DeleteMapping(value = "${url.acts}" + "/{entityId}")
    public void deleteEntity(@PathVariable Long entityId) {
        actDbService.deleteById(entityId);
    }
    
    @DeleteMapping(value = "${url.acts}")
    public void deleteAll() {
        actDbService.deleteAll();
    }
    
    @GetMapping(value = "${url.acts.keywords}")
    public List<String> getAllKeywords(){
        return keywordMapper.mapToStringList(keywordDbService.getAll());
    }
}
