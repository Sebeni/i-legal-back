package pl.seb.czech.ilegal.back.clients.act.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.clients.act.IsapActSearchQuery;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsapActDeserializationTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldDeserializeJsonToAct() {
        String kcJson = "{\n" +
                "  \"address\": \"WDU19640160093\",\n" +
                "  \"publisher\": \"WDU\",\n" +
                "  \"year\": 1964,\n" +
                "  \"volume\": 16,\n" +
                "  \"pos\": 93,\n" +
                "  \"type\": \"Ustawa\",\n" +
                "  \"title\": \"Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny.\",\n" +
                "  \"status\": \"akt posiada tekst jednolity\",\n" +
                "  \"displayAddress\": \"Dz.U. 1964 nr 16 poz. 93\",\n" +
                "  \"promulgation\": \"1964-05-18\",\n" +
                "  \"announcementDate\": \"1964-04-23\",\n" +
                "  \"changeDate\": \"2020-04-14 16:00:00\",\n" +
                "  \"ELI\": \"DU/1964/93\"\n" +
                "}";


        IsapAct actFromJson = new IsapAct();
        try {
            actFromJson = objectMapper.readValue(kcJson, IsapAct.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        IsapAct finalAct = actFromJson;
        assertAll(
                () -> assertEquals("WDU19640160093", finalAct.getIsapId()),
                () -> assertEquals(ActPublisher.WDU, finalAct.getPublisher()),
                () -> assertEquals(1964, finalAct.getYear()),
                () -> assertEquals(16, finalAct.getVolume()),
                () -> assertEquals(93, finalAct.getPosition()),
                () -> assertEquals("Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny.", finalAct.getTitle()),
                () -> assertEquals("akt posiada tekst jednolity", finalAct.getStatus()),
                () -> assertEquals(LocalDate.of(1964,5,18), finalAct.getPromulgation()),
                () -> assertEquals(LocalDateTime.of(2020, 4, 14, 16,0,0), finalAct.getChangeDate())
        );
    }


    @Test
    void bla() throws Exception {
        IsapActSearchQuery isapActSearchQuery = new IsapActSearchQuery("a", "tilte", "key", "prop", ActPublisher.WDU, 1, 1, 0);
        System.out.println(objectMapper.writeValueAsString(isapActSearchQuery));


    }
}