package pl.seb.czech.ilegal.back.controllers.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.clients.act.IsapActTextType;
import pl.seb.czech.ilegal.back.clients.act.IsapClient;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchQueryDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.act.ActSearchQueryMapper;
import pl.seb.czech.ilegal.back.mappers.act.IsapMapper;

import java.net.URI;

@RestController
@RequestMapping(value = "${url.base.search}")
@Slf4j
public class IsapController {
    @Autowired
    private IsapClient isapClient;
    @Autowired
    private IsapMapper isapMapper;
    @Autowired
    private ActSearchQueryMapper queryMapper;
    
    @PostMapping(value = "${url.acts.isap}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActSearchResultDto performSearch(@RequestBody ActSearchQueryDto searchQuery){
        return isapMapper.mapToActSearchResultDto(isapClient.performSearchQuery(queryMapper.mapToQuery(searchQuery)));
    }
    
    @GetMapping(value = "${url.acts.isap.text.link}" + "/{isapId}" + "/{textType}")
    public URI getTxtDownloadUri(@PathVariable String isapId, @PathVariable IsapActTextType textType){
        return isapClient.generateDownloadActURI(isapId, textType);
    }
    
    @GetMapping(value = "${url.acts.isap.text.link.check}")
    public boolean checkIfTextExists(@RequestParam String uri) {
        boolean exists = false;
        try{
            exists = isapClient.validateTxtExists(uri);
        } catch (Exception e){
            log.error("Error while checking if text exists", e);
        }
        return exists;
    }
}
