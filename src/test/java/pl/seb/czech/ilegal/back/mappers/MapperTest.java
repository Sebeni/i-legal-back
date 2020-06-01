package pl.seb.czech.ilegal.back.mappers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class MapperTest<E, EDto> {
    List<E> entityList;
    List<EDto> dtoList;
    protected ListMapper<E, EDto> mapper;


    public MapperTest(E[] entityArray, EDto[] dtoArray, ListMapper<E, EDto> mapper) {
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

