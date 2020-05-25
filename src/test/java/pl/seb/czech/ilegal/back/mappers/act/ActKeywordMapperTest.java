package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.act.dto.ActKeywordDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.mappers.MapperTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActKeywordMapperTest extends MapperTest<ActKeywordMapper, ActKeyword, ActKeywordDto> {
    
    static ActKeyword[] entityArray = {new ActKeyword(), new ActKeyword()};
    static ActKeywordDto[] dtoArray = {new ActKeywordDto(), new ActKeywordDto()};

    @Autowired
    public ActKeywordMapperTest(ActKeywordMapper mapper) {
        super(entityArray, dtoArray, mapper);
    }

    @Test
    void mapToActKeyword() {
        String name = "name";

        ActKeywordDto akd = new ActKeywordDto(name);

        ActKeyword result = mapper.mapToEntity(akd);

        assertEquals(name, result.getName());
    }

    @Test
    void mapToActKeywordDto() {
        String name = "name";

        ActKeyword ak = new ActKeyword(name);

        ActKeywordDto result = mapper.mapToDto(ak);

        assertEquals(name, result.getName());
    }
}