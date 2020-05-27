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
    public List<ActDto> getAllActs() {
        return actMapper.mapToDtoList(actDbService.getAll());
    }
    
    @GetMapping(value = "${url.acts}" + "/{entityId}")
    public ActDto getActById(@PathVariable Long entityId) {
        return actMapper.mapToDto(actDbService.getById(entityId));
    }
    
    @GetMapping(value = "${url.acts.byIsapId}", params = "id")
    public ActDto getActByIsapId(@RequestParam String id) {
        return actMapper.mapToDto(actDbService.findByIsapId(id));
    }
    
    @PostMapping(value = "${url.acts}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActDto saveAct(@RequestBody ActDto actDto) {
        return actMapper.mapToDto(actDbService.save(actMapper.mapToEntity(actDto)));
    }
    
    @PutMapping(value = "${url.acts}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActDto updateAct(@RequestBody ActDto actDto) {
        return actMapper.mapToDto(actDbService.update(actMapper.mapToEntity(actDto)));
    }
    
    @DeleteMapping(value = "${url.acts}" + "/{entityId}")
    public void deleteAct(@PathVariable Long entityId) {
        actDbService.deleteById(entityId);
    }
    
    @DeleteMapping(value = "${url.acts.deleteAll}")
    public void deleteAll() {
        actDbService.deleteAll();
    }
    
    @GetMapping(value = "${url.acts.keywords}")
    public List<String> getAllKeywords(){
        return keywordMapper.mapToStringList(keywordDbService.getAll());
    }
}
