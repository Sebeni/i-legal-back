package pl.seb.czech.ilegal.back.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class DeleteLogDto {
    protected Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime timeStamp;
    protected String sourceName;
    protected Long beforeDeleteElementCount;
    protected DeleteType deleteType;
}
