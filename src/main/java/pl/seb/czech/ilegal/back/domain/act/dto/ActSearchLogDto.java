package pl.seb.czech.ilegal.back.domain.act.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.SearchLogDto;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ActSearchLogDto extends SearchLogDto {
    private String onlyActInForce;
    private String title;
    private String keyWord;
    private String properName;
    private ActPublisher publisher;
    private Integer year;
    private Integer position;
    
    public ActSearchLogDto(IsapActSearchQuery query) {
        this.onlyActInForce = query.getOnlyActInForce();
        this.title = query.getTitle();
        this.keyWord = query.getKeyWord();
        this.properName = query.getProperName();
        this.publisher = query.getPublisher();
        this.year = query.getYear();
        this.position = query.getPosition();
    }

    public ActSearchLogDto(Long id, LocalDateTime createdOn, Long responseTime, Integer resultCount, String onlyActInForce, String title, String keyWord, String properName, ActPublisher publisher, Integer year, Integer position) {
        super(id, createdOn, responseTime, resultCount);
        this.onlyActInForce = onlyActInForce;
        this.title = title;
        this.keyWord = keyWord;
        this.properName = properName;
        this.publisher = publisher;
        this.year = year;
        this.position = position;
    }
}
