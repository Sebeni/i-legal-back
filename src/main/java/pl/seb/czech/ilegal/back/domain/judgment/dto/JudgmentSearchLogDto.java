package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.clients.judgment.SaosJudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.back.domain.SearchLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class JudgmentSearchLogDto extends SearchLogDto {
    public JudgmentSearchLogDto(Long id, LocalDateTime createdOn, String searchUrl, Integer resultCount) {
        super(id, createdOn, searchUrl, resultCount);
    }
}
