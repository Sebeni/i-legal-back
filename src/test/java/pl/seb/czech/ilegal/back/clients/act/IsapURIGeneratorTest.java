package pl.seb.czech.ilegal.back.clients.act;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapURIGeneratorTest {
    private static final String isapId = "WDU19640160093";
    
    @Autowired
    private IsapURIGenerator isapURIGenerator;
   

    @Test
    void shouldGenerateURIToPublishedAct() {
        String uri = isapURIGenerator.generateDownloadActURI(isapId, IsapActTextType.PUBLISHED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1964/0093/text/O/D19640093.pdf", uri);
    }

    @Test
    void shouldGenerateURIToUnifiedAct() {
        String uri = isapURIGenerator.generateDownloadActURI(isapId, IsapActTextType.UNIFIED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1964/0093/text/U/D19640093Lj.pdf", uri);
    }
    
    @Test
    void shouldGenerateURIToSearch() {
        IsapActSearchQuery query = new IsapActSearchQuery();
        query.setKeyWord("absolwenci");
        query.setProperName("Australia");

        String uriResult = isapURIGenerator.generateSearchQueryUri(query).toString();

        String expected = "http://isap.sejm.gov.pl/api/isap/search?keyword=absolwenci&keywordName=Australia" +
                String.format("&limit=%d&offset=%d", query.getResultLimitPerPage(), query.getOffset());

        assertEquals(expected, uriResult);
    }
  
}