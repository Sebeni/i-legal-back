package pl.seb.czech.ilegal.back.mappers.judgment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.back.domain.DeleteType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDeleteLog;
import pl.seb.czech.ilegal.back.mappers.DeleteLogMapper;
import pl.seb.czech.ilegal.back.mappers.DeleteLogMapperTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgmentDeleteLogMapperTest extends DeleteLogMapperTest<JudgmentDeleteLog, JudgmentDeleteLogDto> {

    @Autowired
    public JudgmentDeleteLogMapperTest(JudgmentDeleteLogMapper mapper) {
        super(mapper, new JudgmentDeleteLog(DeleteType.ALL, "s", 1L));
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