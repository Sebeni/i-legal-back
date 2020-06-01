package pl.seb.czech.ilegal.back.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.seb.czech.ilegal.back.clients.judgment.SaosClient;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchQueryDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentSearchQueryMapper;
import pl.seb.czech.ilegal.back.mappers.judgment.SaosMapper;

@RestController
@RequestMapping(value = "${url.base.search}")
public class SaosController {
    @Autowired
    private SaosMapper saosMapper;
    @Autowired
    private SaosClient saosClient;
    @Autowired
    private JudgmentSearchQueryMapper searchMapper;

    @PostMapping(value = "${url.judgments.saos}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JudgmentSynopsisSearchResultDto performSearch(@RequestBody JudgmentSearchQueryDto searchQuery){
        return saosMapper.mapToJudgmentSynopsisSearchResultDto(saosClient.performSearchQuery(searchMapper.mapToQuery(searchQuery)));
    }
    
    @GetMapping(value = "${url.judgments.saos.details}" + "/{saosId}")
    public JudgmentDetailsDto searchForJudgmentDetails(@PathVariable String saosId){
        return saosMapper.mapToJudgmentDetailDto(saosClient.getJudgmentDetails(saosId));
    }
}
