package pl.seb.czech.ilegal.back.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReferencedRegulationDto {
    private String title;
    private Integer year;
    private Integer volume;
    private Integer position;
    private String text;
}
