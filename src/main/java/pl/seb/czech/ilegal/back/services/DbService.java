package pl.seb.czech.ilegal.back.services;

import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.BaseEntity;
import java.util.Set;

public abstract class DbService<T extends BaseEntity<ID>, ID> {
    protected CrudRepository<T, ID> repository;

    public DbService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T save(T entityToSave) {
        return repository.save(entityToSave);
    }

    public T update(T entityToUpdate) throws ElementNotFound {
        ID actId = entityToUpdate.getId();

        if(findIfExistsById(actId)) {
            return save(entityToUpdate);
        } else {
            throw new ElementNotFound(actId);
        }
    }

    public boolean findIfExistsById(ID id) {
        return repository.existsById(id);
    }
    
    public Set<T> getAll() {
        return (Set<T>) repository.findAll();
    }
    
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    public void deleteAll() {
        repository.deleteAll();
    }
    
    public T getById(ID id) {
        return repository.findById(id).orElseThrow(() -> new ElementNotFound(id));
    }
}
