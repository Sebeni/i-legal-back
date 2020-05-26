package pl.seb.czech.ilegal.back.repositories.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;
import pl.seb.czech.ilegal.back.repositories.act.ActKeywordRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActKeywordRepositoryTest extends RepositoryTest<ActKeyword, String> {
    private static String name = "TestTest";
 
    
    @Test
    void shouldSaveAndGetName() {
        saveEntityInRepo();
        
        ActKeyword keywordFromRepo = getEntityFromRepoById();
        assertEquals(name, keywordFromRepo.getName());
        
        deleteEntityFromRepo();
    }

    @Autowired
    public ActKeywordRepositoryTest(ActKeywordRepository repository) {
        super(repository, new ActKeyword(name));
    }
}