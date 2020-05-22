package pl.seb.czech.ilegal.back.client.act;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.client.act.responses.IsapAct;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapActFilenameGeneratorTest {
    private static IsapAct upiol;
    private static IsapAct taxRates;

    @Autowired
    private IsapActFilenameGenerator generator;

    @BeforeAll
    static void initAct() {
        upiol = new IsapAct("WDU19910090031", null, null, null, null, null, null, null, null, null, null);
        taxRates = new IsapAct("WMP20190000738", null, null, null, null, null, null, null, null, null, null);
    }

    @Test
    void shouldReturnGeneratedUnifiedTxtFilename() {
        assertAll(
                () -> assertEquals("D19910031Lj.pdf", generator.generateTxtFilename(upiol, IsapActTextType.UNIFIED)),
                () -> assertEquals("M20190738Lj.pdf", generator.generateTxtFilename(taxRates, IsapActTextType.UNIFIED))
        );
    }

    @Test
    void shouldReturnGeneratedPublishedTxtFileName() {
        assertAll(
                () -> assertEquals("D19910031.pdf", generator.generateTxtFilename(upiol, IsapActTextType.PUBLISHED)),
                () -> assertEquals("M20190738.pdf", generator.generateTxtFilename(taxRates, IsapActTextType.PUBLISHED))
        );
    }
    
}