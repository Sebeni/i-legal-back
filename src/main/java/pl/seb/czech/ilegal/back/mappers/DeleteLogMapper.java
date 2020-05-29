package pl.seb.czech.ilegal.back.mappers;

import pl.seb.czech.ilegal.back.domain.DeleteLog;
import pl.seb.czech.ilegal.back.domain.DeleteLogDto;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class DeleteLogMapper<T extends DeleteLog, TDto extends DeleteLogDto> {
    private Supplier<TDto> deleteLogDtoSupplier;
    
    public TDto mapToDeleteLogDto (T deleteLog){
        TDto deleteLogDto = deleteLogDtoSupplier.get();
        deleteLogDto.setId(deleteLog.getId());
        deleteLogDto.setTimeStamp(deleteLog.getTimeStamp());
        deleteLogDto.setBeforeDeleteElementCount(deleteLog.getBeforeDeleteElementCount());
        deleteLogDto.setDeleteType(deleteLog.getDeleteType());
        deleteLogDto.setSourceName(deleteLog.getSourceName());
        return deleteLogDto;
    }
    
    public List<TDto> mapToDeleteLogDtoList(List<T> deleteLogList) {
        return deleteLogList.stream()
                .map(this::mapToDeleteLogDto)
                .collect(Collectors.toList());
    }

    public DeleteLogMapper(Supplier<TDto> deleteLogDtoSupplier) {
        this.deleteLogDtoSupplier = deleteLogDtoSupplier;
    }
}
