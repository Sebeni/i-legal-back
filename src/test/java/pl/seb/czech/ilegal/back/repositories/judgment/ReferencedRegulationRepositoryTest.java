package pl.seb.czech.ilegal.back.repositories.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.judgment.entity.ReferencedRegulation;
import pl.seb.czech.ilegal.back.repositories.RepositoryTest;
import pl.seb.czech.ilegal.back.repositories.judgment.ReferencedRegulationRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReferencedRegulationRepositoryTest extends RepositoryTest<ReferencedRegulation, String> {
    public static String title = "Test";
    public static Integer year = 1994;
    public static Integer volume = 24;
    public static Integer position = 83;
    public static String text = "text test";

    @Test
    void shouldSaveAndGetEntityWithProperties() {
        saveEntityInRepo();
        
        ReferencedRegulation refReg = getEntityFromRepoById();
        
        assertAll(
                () -> assertEquals(title, refReg.getTitle()),
                () -> assertEquals(year, refReg.getYear()),
                () -> assertEquals(volume, refReg.getVolume()),
                () -> assertEquals(position, refReg.getPosition()),
                () -> assertEquals(text, refReg.getText())
        );
        
        deleteEntityFromRepo();
    }
    
    @Autowired
    public ReferencedRegulationRepositoryTest(ReferencedRegulationRepository repository) {
        super(repository, new ReferencedRegulation(title, year, volume, position, text));
    }
}