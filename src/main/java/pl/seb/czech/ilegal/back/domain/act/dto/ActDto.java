package pl.seb.czech.ilegal.back.domain.act.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActDto {
    private Long id;
    private String isapId;
    private ActPublisher publisher;
    private Integer year;
    private Integer volume;
    private Integer position;
    private String title;
    private String status;
    private LocalDate promulgation;
    private LocalDateTime changeDate;
    private String publishedTextUrl;
    private String unifiedTextUrl;
}
