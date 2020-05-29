package pl.seb.czech.ilegal.back.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchLogDto;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentDeleteLogMapper;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentSearchLogMapper;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentDeleteLogDbService;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentSearchLogDbService;

import java.util.List;

@RestController
@RequestMapping(value = "${url.base.history}")
public class JudmentHistoryController {
    @Autowired
    private JudgmentSearchLogDbService searchLogDbService;
    @Autowired
    private JudgmentSearchLogMapper searchLogMapper;
    @Autowired
    private JudgmentDeleteLogDbService deleteLogDbService;;
    @Autowired
    private JudgmentDeleteLogMapper deleteLogMapper;

    @GetMapping(value = "${url.judgments.history.log.search}")
    public List<JudgmentSearchLogDto> getSearchLog() {
        return searchLogMapper.mapToDtoList(searchLogDbService.getAll());
    }

    @DeleteMapping(value = "${url.judgments.history.log.search}")
    public void deleteLog(){
        searchLogDbService.deleteAll();
    }

    @GetMapping(value = "${url.judgments.history.log.delete}")
    public List<JudgmentDeleteLogDto> getDeleteLog() {
        return deleteLogMapper.mapToDeleteLogDtoList(deleteLogDbService.getAllDeleteLogs());
    }
}
