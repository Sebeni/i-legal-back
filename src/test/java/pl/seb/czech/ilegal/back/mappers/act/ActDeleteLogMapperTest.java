package pl.seb.czech.ilegal.back.mappers.act;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActDeleteLog;
import pl.seb.czech.ilegal.back.mappers.DeleteLogMapper;
import pl.seb.czech.ilegal.back.mappers.DeleteLogMapperTest;

@SpringBootTest
class ActDeleteLogMapperTest extends DeleteLogMapperTest<ActDeleteLog, ActDeleteLogDto> {

    @Autowired
    public ActDeleteLogMapperTest(ActDeleteLogMapper mapper) {
        super(mapper, new ActDeleteLog(DeleteType.ALL, "src", -5L));
    }


    @Test
    void mapToDeleteLogDtoTest(){
        mapToDeleteLogDto();
    }

    @Test
    void mapToDeleteLogDtoListTest(){
        mapToDeleteLogDtoList();
    }
    
}