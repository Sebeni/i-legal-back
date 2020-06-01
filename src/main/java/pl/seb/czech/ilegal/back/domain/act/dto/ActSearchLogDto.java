package pl.seb.czech.ilegal.back.domain.act.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.SearchLogDto;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ActSearchLogDto extends SearchLogDto {
    public ActSearchLogDto(Long id, LocalDateTime createdOn, String searchUrl, Integer resultCount) {
        super(id, createdOn, searchUrl, resultCount);
    }
}
