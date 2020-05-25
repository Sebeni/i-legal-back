package pl.seb.czech.ilegal.back.mappers;

import org.junit.jupiter.api.Test;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class MapperTest<T extends ListMapper<E, EDto>, E, EDto> {
    List<E> entityList;
    List<EDto> dtoList;
    protected T mapper;


    public MapperTest(E[] entityArray, EDto[] dtoArray, T mapper) {
        this.entityList = Arrays.asList(entityArray);
        this.dtoList = Arrays.asList(dtoArray);
        this.mapper = mapper;
    }

    @Test
    void mapToEntityList() {
        List<E> result = mapper.mapToEntityList(dtoList);

        assertEquals(dtoList.size(), result.size());
    }

    @Test
    void mapToDtoList() {
        List<EDto> result = mapper.mapToDtoList(entityList);
        assertEquals(entityList.size(), result.size());
    }
}

