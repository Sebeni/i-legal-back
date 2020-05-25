package pl.seb.czech.ilegal.back.domain.act.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private LocalDate promulgationDate;
    private LocalDateTime changeDate;
    private String publishedTextUrl;
    private String unifiedTextUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActDto actDto = (ActDto) o;
        return Objects.equals(id, actDto.id) &&
                isapId.equals(actDto.isapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isapId);
    }
}
