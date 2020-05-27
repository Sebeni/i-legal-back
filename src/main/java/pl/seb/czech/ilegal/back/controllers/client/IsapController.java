package pl.seb.czech.ilegal.back.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.clients.act.IsapActTextType;
import pl.seb.czech.ilegal.back.clients.act.IsapClient;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.act.IsapMapper;

import java.net.URI;

@RestController
@RequestMapping(value = "${url.base.search}")
public class IsapController {
    @Autowired
    private IsapClient isapClient;
    @Autowired
    private IsapMapper isapMapper;
    
    @GetMapping(value = "${url.isap}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActSearchResultDto performSearch(@RequestBody IsapActSearchQuery searchQuery){
        return isapMapper.mapToActSearchResultDto(isapClient.performSearchQuery(searchQuery));
    }
    
    @GetMapping(value = "${url.isap.text.link}" + "/{textType}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public URI getTxtDownloadUri(@RequestBody ActDto actDto, @PathVariable IsapActTextType textType){
        return isapClient.generateDownloadActURI(isapMapper.mapToIsapAct(actDto), textType);
    }
    
    @GetMapping(value = "${url.isap.text.link.check}")
    public boolean checkIfTextExists(@RequestParam String uri) {
        return isapClient.validateTxtExists(uri);
    }
    
    
}
