package pl.seb.czech.ilegal.back.mappers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ListMapper<E, EDto> {
    public List<E> mapToEntityList(List<EDto> dtoList) {
        return dtoList.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
    
    public List<EDto> mapToDtoList(List<E> entityList) {
        return entityList.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    public abstract E mapToEntity(EDto dto);
    
    public abstract EDto mapToDto(E entity);
}
