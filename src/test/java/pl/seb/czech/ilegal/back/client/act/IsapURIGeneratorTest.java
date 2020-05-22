package pl.seb.czech.ilegal.back.client.act;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.client.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapURIGeneratorTest {
    private static String isapId = "WDU19640160093";
    private static ActPublisher ap = ActPublisher.WDU;
    private static Integer year = 1964;
    private static Integer volume = 16;
    private static Integer position = 93;
    private static String title = "Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny.";
    private static String status = "akt posiada tekst jednolity";
    private static LocalDate promulgation = LocalDate.of(1964, 5, 18);
    private static LocalDateTime changeDate = LocalDateTime.of(2020, 1, 1, 1, 1, 1);
    private static String publishUrl = "abc";
    private static String unifiedUrl = "cde";
    
    private static IsapAct actToDownload;
    @Autowired
    private IsapURIGenerator isapURIGenerator;

    @BeforeAll
    static void initAct(){
        actToDownload = new IsapAct(isapId, ap, year, volume, position, title, status, promulgation, changeDate, publishUrl, unifiedUrl);
    }

    @Test
    void shouldGenerateURIToPublishedAct() {
        String uri = isapURIGenerator.generateDownloadActURI(actToDownload, IsapActTextType.PUBLISHED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1964/93/text/O/D19640093.pdf", uri);
    }

    @Test
    void shouldGenerateURIToUnifiedAct() {
        String uri = isapURIGenerator.generateDownloadActURI(actToDownload, IsapActTextType.UNIFIED).toString();
        assertEquals("http://isap.sejm.gov.pl/api/isap/deeds/WDU/1964/93/text/U/D19640093Lj.pdf", uri);
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