package pl.seb.czech.ilegal.back.controllers.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.mappers.act.ActDeleteLogMapper;
import pl.seb.czech.ilegal.back.mappers.act.ActSearchLogMapper;
import pl.seb.czech.ilegal.back.services.act.ActDeleteLogDbService;
import pl.seb.czech.ilegal.back.services.act.ActSearchLogDbService;


import java.util.List;

@RestController
@RequestMapping(value = "${url.base.history}")
public class ActHistoryController {
    @Autowired
    private ActSearchLogDbService searchLogDbService;
    @Autowired
    private ActSearchLogMapper searchLogMapper;
    @Autowired
    private ActDeleteLogDbService deleteLogDbService;
    @Autowired
    private ActDeleteLogMapper deleteLogMapper;

    @GetMapping(value = "${url.acts.history.log.search}")
    public List<ActSearchLogDto> getSearchLog() {
        return searchLogMapper.mapToDtoList(searchLogDbService.getAll());
    }
    
    @DeleteMapping(value = "${url.acts.history.log.search}")
    public void clearSearchLog(){
        searchLogDbService.deleteAll();
    }
    
    @GetMapping(value = "${url.acts.history.log.delete}")
    public List<ActDeleteLogDto> getDeleteLog() {
        return deleteLogMapper.mapToDeleteLogDtoList(deleteLogDbService.getAllDeleteLogs());
    }
}
