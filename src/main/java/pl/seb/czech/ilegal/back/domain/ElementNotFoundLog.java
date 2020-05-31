package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.seb.czech.ilegal.back.services.ElementNotFound;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ElementNotFoundLog {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(updatable = false)
    private LocalDateTime timeStamp = NowTime.generate();
    
    
    private String message;

    public ElementNotFoundLog(String message) {
        this.message = message;
    }
}
