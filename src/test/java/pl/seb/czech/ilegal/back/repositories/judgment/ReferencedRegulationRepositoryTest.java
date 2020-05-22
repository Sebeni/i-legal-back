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
    public static String title = "Ustawa z dnia 4 lutego 1994 r. o prawie autorskim i prawach pokrewnych";
    public static Integer year = 1994;
    public static Integer volume = 24;
    public static Integer position = 83;
    public static String text = "Ustawa z dnia 4 lutego 1994 r. o prawie autorskim i prawach pokrewnych (Dz. U. z 1994 r. Nr 24, poz. 83 - art. 78; art. 78 ust. 1; art. 81; art. 83)";

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