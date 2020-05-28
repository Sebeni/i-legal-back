package pl.seb.czech.ilegal.back.clients.act;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapActFilenameGeneratorTest {
    private static String upiol;
    private static String taxRates;

    @Autowired
    private IsapActFilenameGenerator generator;

    @BeforeAll
    static void initAct() {
        upiol = "WDU19910090031";
        taxRates = "WMP20190000738";
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