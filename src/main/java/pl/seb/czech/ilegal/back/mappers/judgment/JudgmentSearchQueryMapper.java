package pl.seb.czech.ilegal.back.mappers.judgment;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.judgment.SaosJudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchQueryDto;

@Component
public class JudgmentSearchQueryMapper {
    public SaosJudgmentSynopsisSearchQuery mapToQuery(JudgmentSearchQueryDto dto){
        return new SaosJudgmentSynopsisSearchQuery(dto.getCourtType(), dto.getSignature(), dto.getArticleNumber(),
                dto.getReferencedRegulationYearPos(), dto.getSearchPhrase(), dto.getPageNumber(), dto.getSortingField(), 
                dto.getSortingDirection());
    }
}
