package pl.seb.czech.ilegal.back.clients.act;

import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;

import java.util.List;

public interface ActDifferenceCheckerFacade {
    List<ActDifferenceDto> getActDifferences(List<Long> actsId);
}
