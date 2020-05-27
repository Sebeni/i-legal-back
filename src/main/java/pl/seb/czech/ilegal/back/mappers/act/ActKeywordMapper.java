package pl.seb.czech.ilegal.back.mappers.act;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.act.dto.ActKeywordDto;
import pl.seb.czech.ilegal.back.domain.act.entity.ActKeyword;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActKeywordMapper extends ListMapper<ActKeyword, ActKeywordDto> {
    
    @Override
    public ActKeyword mapToEntity(ActKeywordDto akd) {
        return new ActKeyword(akd.getName());
    }

    @Override
    public ActKeywordDto mapToDto(ActKeyword ak) {
        return new ActKeywordDto(ak.getName());
    }
    
    public List<String> mapToStringList(List<ActKeyword> keywords){
        return keywords.stream().map(ActKeyword::getName).collect(Collectors.toList());
    }
}
