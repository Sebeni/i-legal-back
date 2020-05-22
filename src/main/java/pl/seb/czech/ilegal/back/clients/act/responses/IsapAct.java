package pl.seb.czech.ilegal.back.clients.act.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IsapAct {
    @JsonProperty(value = "address")
    private String isapId;
    private ActPublisher publisher;
    private Integer year;
    private Integer volume;
    
    @JsonProperty(value = "pos")
    private Integer position;
    private String title;
    private String status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate promulgation;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime changeDate;
    private String publishedTextUrl;
    private String unifiedTextUrl;
}
