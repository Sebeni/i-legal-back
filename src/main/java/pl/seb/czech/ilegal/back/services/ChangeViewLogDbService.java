package pl.seb.czech.ilegal.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.domain.ChangeViewLog;
import pl.seb.czech.ilegal.back.repositories.ChangeViewLogRepository;

@Service
public class ChangeViewLogDbService {
    private ChangeViewLogRepository repository;
    
    public ChangeViewLog save(ChangeViewLog entity){
        return repository.save(entity);
    }

    @Autowired
    public ChangeViewLogDbService(ChangeViewLogRepository repository) {
        this.repository = repository;
    }
}
