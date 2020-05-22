package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.clients.judgment.SaosJudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.back.domain.SearchLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JudgmentSearchLogDto extends SearchLogDto {
    private CourtType courtType;
    private String signature;
    private Integer articleNumber;
    private String referencedRegulationYearPos;
    private String searchPhrase;


    public JudgmentSearchLogDto(SaosJudgmentSynopsisSearchQuery query) {
        this.courtType = query.getCourtType();
        this.signature = query.getSignature();
        this.articleNumber = query.getArticleNumber();
        this.referencedRegulationYearPos = query.getReferencedRegulationYearPos();
        this.searchPhrase = query.getSearchPhrase();
    }

    public JudgmentSearchLogDto(Long id, LocalDateTime createdOn, Long responseTime, Integer resultCount, CourtType courtType, String signature, Integer articleNumber, String referencedRegulationYearPos, String searchPhrase) {
        super(id, createdOn, responseTime, resultCount);
        this.courtType = courtType;
        this.signature = signature;
        this.articleNumber = articleNumber;
        this.referencedRegulationYearPos = referencedRegulationYearPos;
        this.searchPhrase = searchPhrase;
    }
}
