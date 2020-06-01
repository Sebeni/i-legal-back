package pl.seb.czech.ilegal.back.services.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.services.ElementNotFound;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActDbServiceTest {
    @Autowired
    ActDbService actDbService;
    
    @Test
    void shouldThrowError(){
        assertThrows(ElementNotFound.class, () -> actDbService.getById(-100L));
    }
}