package pl.seb.czech.ilegal.back.mappers.judgment;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentDetails;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsis;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentSynopsisSearchResult;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosReferencedRegulation;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisSearchResultDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.ReferencedRegulationDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;
import pl.seb.czech.ilegal.back.mappers.ResultQueryMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SaosMapper extends ResultQueryMapper<SaosJudgmentSynopsis, JudgmentSynopsisDto> {
    
    public JudgmentSynopsisSearchResultDto mapToJudgmentSynopsisSearchResultDto(SaosJudgmentSynopsisSearchResult searchResult) {
        return new JudgmentSynopsisSearchResultDto(mapToDtoList(searchResult.getResults()), 
                searchResult.getNumOfResults(), searchResult.getPageNumber());
    }
    
    @Override
    public JudgmentSynopsisDto mapToDto(SaosJudgmentSynopsis searchResultElement) {
        return new JudgmentSynopsisDto(null,
                searchResultElement.getSaosId(),
                searchResultElement.getCourtType(),
                searchResultElement.getCaseNumbers(),
                searchResultElement.getJudgmentType(),
                searchResultElement.getCustomName(),
                searchResultElement.getSynopsis(),
                searchResultElement.getJudgmentDate(),
                null);
    }

    public JudgmentDetailsDto mapToJudgmentDetailDto(SaosJudgmentDetails saosDetails){
        return new JudgmentDetailsDto(null, saosDetails.getSaosId(), saosDetails.getTextContent(), 
                mapToReferencedRegulationDtoSet(saosDetails.getSaosReferencedRegulations()),
                saosDetails.getLegalBases(), saosDetails.getKeywords());
    }
    
    public ReferencedRegulationDto mapToReferencedRegulationDto(SaosReferencedRegulation saosRefReg) {
        return new ReferencedRegulationDto(saosRefReg.getTitle(), saosRefReg.getYear(), saosRefReg.getVolume(), 
                saosRefReg.getPosition(), saosRefReg.getText());
    }
    
    public Set<ReferencedRegulationDto> mapToReferencedRegulationDtoSet(Set<SaosReferencedRegulation> saosRefRegSet){
        if(saosRefRegSet != null && saosRefRegSet.size() > 0) {
            return saosRefRegSet.stream().map(this::mapToReferencedRegulationDto).collect(Collectors.toSet());
        } else {
            return null;
        }
    }
    
}
