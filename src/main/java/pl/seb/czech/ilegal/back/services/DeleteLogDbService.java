package pl.seb.czech.ilegal.back.services;

import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;

import java.util.List;
import java.util.function.Supplier;

public abstract class DeleteLogDbService<T extends DeleteLog> {
    private final CrudRepository<T, Long> deleteRepository;
    private final Supplier<T> supplier;

    public DeleteLogDbService(CrudRepository<T, Long> deleteRepository, Supplier<T> supplier) {
        this.deleteRepository = deleteRepository;
        this.supplier = supplier;
    }

    public void saveIfDeleted(DeleteType deleteType, String sourceName, long beforeDeleteRepositoryCount) {
        if(beforeDeleteRepositoryCount > 0){
            T deleteLogToSave = supplier.get();
            deleteLogToSave.setDeleteType(deleteType);
            deleteLogToSave.setSourceName(sourceName);
            deleteLogToSave.setBeforeDeleteElementCount(beforeDeleteRepositoryCount);
            deleteRepository.save(deleteLogToSave);
        }
    }

    public List<T> getAllDeleteLogs(){
        return (List<T>) deleteRepository.findAll();
    }
}
