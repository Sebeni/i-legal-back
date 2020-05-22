package pl.seb.czech.ilegal.back.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import pl.seb.czech.ilegal.back.domain.BaseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class RepositoryTest<T extends BaseEntity<ID>, ID> {
    protected CrudRepository<T, ID> repository;
    protected Long initialEntityCount;
    protected T testedEntity;

    public RepositoryTest(CrudRepository<T, ID> repository, T testedEntity) {
        this.repository = repository;
        this.testedEntity = testedEntity;
        initialEntityCount = repository.count();
    }
    
    @Test
    void shouldSaveAndDelete() {
        saveEntityInRepo();
        deleteEntityFromRepo();
    }

    @Test
    void shouldSaveAndGet() {
        saveEntityInRepo();
        ID key = testedEntity.getId();

        T entityFromRepo = getEntityFromRepoById();
        assertEquals(key, entityFromRepo.getId());

        deleteEntityFromRepo();
    }

    protected T saveEntityInRepo() {
        T savedEntity = repository.save(testedEntity);
        assertEquals(initialEntityCount + 1, repository.count());
        return savedEntity; 
    }

    protected T getEntityFromRepoById() {
        Optional<T> optionalFromRepo = repository.findById(testedEntity.getId());
        assertTrue(optionalFromRepo.isPresent());
        return optionalFromRepo.get();
    }

    protected void deleteEntityFromRepo() {
        repository.deleteById(testedEntity.getId());
        assertEquals(initialEntityCount, repository.count());
    }
}
