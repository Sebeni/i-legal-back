package pl.seb.czech.ilegal.back.services;

import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import pl.seb.czech.ilegal.back.domain.DeleteType;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class DbService<T extends BaseEntity<ID>, ID> {
    protected CrudRepository<T, ID> repository;
    protected DeleteLogDbService deleteLogDbService;

    public DbService(CrudRepository<T, ID> repository, DeleteLogDbService deleteLogDbService) {
        this.repository = repository;
        this.deleteLogDbService = deleteLogDbService;
    }

    public T save(T entityToSave) {
        return repository.save(entityToSave);
    }

    public T update(T entityToUpdate) {
        ID id = entityToUpdate.getId();

        if(findIfExistsById(id)) {
            return save(entityToUpdate);
        } else {
            throw new ElementNotFound(id);
        }
    }

    public boolean findIfExistsById(ID id) {
        return repository.existsById(id);
    }
    
    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }
    
    public void deleteById(ID id) {
        long repositoryCountBeforeDelete = repository.count();
        repository.deleteById(id);
        deleteLogDbService.saveIfDeleted(DeleteType.BY_ID, getClass().getSimpleName(), repositoryCountBeforeDelete);
    }
    
    public void deleteAll() {
        long repositoryCountBeforeDelete = repository.count();
        repository.deleteAll();
        deleteLogDbService.saveIfDeleted(DeleteType.ALL, getClass().getSimpleName(), repositoryCountBeforeDelete);
    }
    
    public T getById(ID id) {
        return repository.findById(id).orElseThrow(() -> new ElementNotFound(id));
    }
}
