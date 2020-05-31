package pl.seb.czech.ilegal.back.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisDto;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentMapper;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentSynopsisDbService;

import java.util.List;

@RestController
@RequestMapping(value = "${url.base.db}")
public class JudgmentController {
    @Autowired
    private JudgmentSynopsisDbService judgmentDbService;

    @Autowired
    private JudgmentMapper judgmentMapper;

    @GetMapping(value = "${url.judgments}")
    public List<JudgmentSynopsisDto> getAllEntities() {
        return judgmentMapper.mapToDtoList(judgmentDbService.getAll());
    }

    @GetMapping(value = "${url.judgments}" + "/{entityId}")
    public JudgmentSynopsisDto getEntityById(@PathVariable Long entityId) {
        return judgmentMapper.mapToDto(judgmentDbService.getById(entityId));
    }

    @GetMapping(value = "${url.judgments.bySaosId}" + "/{saosId}")
    public boolean checkEntityByApiId(@PathVariable Long saosId) {
        return judgmentDbService.existsBySaosId(saosId);
    }

    @PostMapping(value = "${url.judgments}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JudgmentSynopsisDto saveEntity(@RequestBody JudgmentSynopsisDto jDto) {
        return judgmentMapper.mapToDto(judgmentDbService.save(judgmentMapper.mapToEntity(jDto)));
    }

    @PutMapping(value = "${url.judgments}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JudgmentSynopsisDto updateEntity(@RequestBody JudgmentSynopsisDto jDto) {
        return judgmentMapper.mapToDto(judgmentDbService.update(judgmentMapper.mapToEntity(jDto)));
    }

    @DeleteMapping(value = "${url.judgments}" + "/{entityId}")
    public void deleteEntity(@PathVariable Long entityId) {
        judgmentDbService.deleteById(entityId);
    }

    @DeleteMapping(value = "${url.judgments}")
    public void deleteAll() {
        judgmentDbService.deleteAll();
    }
}
