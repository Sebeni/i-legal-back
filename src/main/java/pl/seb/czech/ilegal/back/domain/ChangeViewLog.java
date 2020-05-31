package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ChangeViewLog {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;
    private String viewName;
    private LocalDateTime timestamp;
}
