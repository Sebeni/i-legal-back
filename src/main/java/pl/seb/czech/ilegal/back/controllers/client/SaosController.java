package pl.seb.czech.ilegal.back.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.seb.czech.ilegal.back.clients.judgment.SaosClient;
import pl.seb.czech.ilegal.back.clients.judgment.SaosJudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsis;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.judgment.SaosMapper;

@RestController
@RequestMapping(value = "${url.base.search}")
public class SaosController {
    @Autowired
    private SaosMapper saosMapper;
    @Autowired
    private SaosClient saosClient;

    @GetMapping(value = "${url.saos}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JudgmentSynopsisSearchResultDto performSearch(@RequestBody SaosJudgmentSynopsisSearchQuery searchQuery){
        return saosMapper.mapToJudgmentSynopsisSearchResultDto(saosClient.performSearchQuery(searchQuery));
    }
    
    @GetMapping(value = "${url.saos.details}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JudgmentDetailsDto getJudmentDetails(@RequestBody SaosJudgmentSynopsis saosJudgmentSynopsis){
        return saosMapper.mapToJudgmentDetailDto(saosClient.getJudgmentDetails(saosJudgmentSynopsis));
    }
}
