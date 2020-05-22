package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentKeyword;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentKeywordRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentKeywordRepositoryTest extends RepositoryTest<JudgmentKeyword, String> {
    private static String name = "dobra osobiste";
    
    @Test
    void shouldSaveAndGetName() {
        saveEntityInRepo();
        
        JudgmentKeyword keywordFromRepo = getEntityFromRepoById();
        assertEquals(name, keywordFromRepo.getName());

        deleteEntityFromRepo();
    }
    

    @Autowired
    public JudgmentKeywordRepositoryTest(JudgmentKeywordRepository repository) {
        super(repository, new JudgmentKeyword(name));
    }
}