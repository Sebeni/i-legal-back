package pl.seb.czech.ilegal.back.clients.act;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapURIGeneratorTest {
    private static final String isapId = "WDU19640160093";
    
    @Autowired
    private IsapURIGenerator isapURIGenerator;
    
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

//    Isap API gave unpredictable results (sometimes files weren't found) so I switched to generate direct links to files
    @Disabled
    @Test
    void shouldGenerateURIToPublishedActWithApi() {
        String uri = isapURIGenerator.generateDownloadActURI(isapId, IsapActTextType.PUBLISHED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1964/0093/text/O/D19640093.pdf", uri);
    }
    @Disabled
    @Test
    void shouldGenerateURIToUnifiedActWithApi() {
        String uri = isapURIGenerator.generateDownloadActURI(isapId, IsapActTextType.UNIFIED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1964/0093/text/U/D19640093Lj.pdf", uri);
    }
    
    @Test
    void shouldGenerateDirectURIToPublishedAct() {
        String uri = isapURIGenerator.generateDownloadActURI(isapId, IsapActTextType.PUBLISHED).toString();
        assertEquals("http://isap.sejm.gov.pl/isap.nsf/download.xsp/WDU19640160093/O/D19640093.pdf", uri);
    }
    
    @Test
    void shouldGenerateDirectURIToUnifiedAct() {
        String uri = isapURIGenerator.generateDownloadActURI(isapId, IsapActTextType.UNIFIED).toString();
        assertEquals("http://isap.sejm.gov.pl/isap.nsf/download.xsp/WDU19640160093/U/D19640093Lj.pdf", uri);
    }
}