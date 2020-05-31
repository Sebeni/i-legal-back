package pl.seb.czech.ilegal.back.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ServerStartLog {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    
    @Column(updatable = false)
    private LocalDateTime timeStamp = NowTime.generate();
}
