package pl.seb.czech.ilegal.back.controllers.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.seb.czech.ilegal.back.domain.ChangeViewLogDto;
import pl.seb.czech.ilegal.back.mappers.ChangeViewLogMapper;
import pl.seb.czech.ilegal.back.services.ChangeViewLogDbService;

@RestController
@RequestMapping(value = "${url.base.history}")
public class ChangeViewLogController {
    @Autowired
    private ChangeViewLogDbService dbService;
    @Autowired
    private ChangeViewLogMapper mapper;
    
    @PostMapping(value = "${url.history.changeview}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChangeViewLogDto saveEntity(@RequestBody ChangeViewLogDto changeViewLogDto){
        return mapper.mapToDto(dbService.save(mapper.mapToEntity(changeViewLogDto)));
    }
}
