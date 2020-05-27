package pl.seb.czech.ilegal.back.mappers.judgment;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSearchLog;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

@Component
public class JudgmentSearchLogMapper extends ListMapper<JudgmentSearchLog, JudgmentSearchLogDto> {
    
    @Override
    public JudgmentSearchLog mapToEntity(JudgmentSearchLogDto jsld) {
        return new JudgmentSearchLog(jsld.getId(), jsld.getCreatedOn(), jsld.getSearchParams(), jsld.getResultCount());
    }

    @Override
    public JudgmentSearchLogDto mapToDto(JudgmentSearchLog jsl) {
        return new JudgmentSearchLogDto(jsl.getId(), jsl.getCreatedOn(), jsl.getSearchParams(), jsl.getResultCount());
    }
}
