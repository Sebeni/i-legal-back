package pl.seb.czech.ilegal.back.mappers;

import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteLogDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class DeleteLogMapperTest<T extends DeleteLog, TDto extends DeleteLogDto> {
    private DeleteLogMapper<T, TDto> mapper;
    private T log;

    protected DeleteLogMapperTest(DeleteLogMapper<T, TDto> mapper, T log) {
        this.mapper = mapper;
        this.log = log;
    }


    protected void mapToDeleteLogDto(){
        TDto result = mapper.mapToDeleteLogDto(log);

        assertAll(
                () -> assertEquals(log.getBeforeDeleteElementCount(), result.getBeforeDeleteElementCount()),
                () -> assertEquals(log.getSourceName(), result.getSourceName()),
                () -> assertEquals(log.getDeleteType(), result.getDeleteType())
        );
    }

    protected void mapToDeleteLogDtoList(){
        List<T> list = new ArrayList<>();
        list.add(log);

        List<TDto> resultList = mapper.mapToDeleteLogDtoList(list);
        TDto result = resultList.get(0);

        assertAll(
                () -> assertEquals(list.size(), resultList.size()),
                () -> assertEquals(log.getBeforeDeleteElementCount(), result.getBeforeDeleteElementCount()),
                () -> assertEquals(log.getSourceName(), result.getSourceName()),
                () -> assertEquals(log.getDeleteType(), result.getDeleteType())
        );
    }
}
