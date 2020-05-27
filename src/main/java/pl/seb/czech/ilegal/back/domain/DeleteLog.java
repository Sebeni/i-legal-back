package pl.seb.czech.ilegal.back.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@MappedSuperclass
public abstract class DeleteLog {
    @Id
    @GeneratedValue
    @NotNull
    protected Long id;
    @Column(updatable = false)
    protected LocalDateTime timeStamp = NowTime.generate();
    @Setter
    protected String sourceName;
    @Setter
    protected Long beforeDeleteElementCount;
    @Setter
    @Enumerated(value = EnumType.STRING)
    protected DeleteType deleteType;

    public DeleteLog(DeleteType deleteType, String sourceName, Long beforeDeleteElementCount) {
        this.deleteType = deleteType;
        this.sourceName = sourceName;
        this.beforeDeleteElementCount = beforeDeleteElementCount;
    }
}
