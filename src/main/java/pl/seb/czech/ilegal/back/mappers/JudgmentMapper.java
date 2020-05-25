package pl.seb.czech.ilegal.back.mappers;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.judgment.dto.*;
import pl.seb.czech.ilegal.back.domain.judgment.entity.*;

import java.util.stream.Collectors;

@Component
public class JudgmentMapper {

    public ReferencedRegulation mapToReferencedRegulation(ReferencedRegulationDto rrd) {
        return new ReferencedRegulation(rrd.getTitle(), rrd.getYear(), rrd.getVolume(),
                rrd.getPosition(), rrd.getText());
    }

    public ReferencedRegulationDto mapToReferencedRegulationDto(ReferencedRegulation rr) {
        return new ReferencedRegulationDto(rr.getTitle(), rr.getYear(), rr.getVolume(),
                rr.getPosition(), rr.getText());
    }

    public JudgmentKeyword mapToJudgmentKeyword(JudgmentKeywordDto jkd) {
        return new JudgmentKeyword(jkd.getName());
    }

    public JudgmentKeywordDto mapToJudgmentKeywordDto(JudgmentKeyword jk) {
        return new JudgmentKeywordDto(jk.getName());
    }

    public JudgmentDetails mapToJudgmentDetails(JudgmentDetailsDto jdd) {
        return new JudgmentDetails(jdd.getId(), jdd.getSaosId(), jdd.getTextContent(),
                jdd.getReferencedRegulations().stream().map(this::mapToReferencedRegulation).collect(Collectors.toSet()),
                jdd.getKeywords().stream().map(this::mapToJudgmentKeyword).collect(Collectors.toSet())
        );
    }

    public JudgmentDetailsDto mapToJudgmentDetailsDto(JudgmentDetails jd) {
        return new JudgmentDetailsDto(jd.getId(), jd.getSaosId(), jd.getTextContent(),
                jd.getReferencedRegulations().stream().map(this::mapToReferencedRegulationDto).collect(Collectors.toSet()),
                jd.getKeywords().stream().map(this::mapToJudgmentKeywordDto).collect(Collectors.toSet())
        );
    }
    
    public JudgmentSynopsis mapToJudgmentSynopsis(JudgmentSynopsisDto jsd) {
        return new JudgmentSynopsis(jsd.getId(), jsd.getSaosId(), jsd.getCourtType(), jsd.getCaseNumbers(), 
                jsd.getJudgmentType(), jsd.getCustomName(), jsd.getSynopsis(), jsd.getJudgmentDate(),
                mapToJudgmentDetails(jsd.getJudgmentDetails()));
    }
    
    public JudgmentSynopsisDto mapToJudgmentSynopsisDto(JudgmentSynopsis js) {
        return new JudgmentSynopsisDto(js.getId(), js.getSaosId(), js.getCourtType(), js.getCaseNumbers(),
                js.getJudgmentType(), js.getCustomName(), js.getSynopsis(), js.getJudgmentDate(),
                mapToJudgmentDetailsDto(js.getJudgmentDetails()));
    }
    
    public JudgmentSearchLog mapToJudgmentSearchLog(JudgmentSearchLogDto jsld) {
        return new JudgmentSearchLog(jsld.getId(), jsld.getCreatedOn(), jsld.getResponseTime(), jsld.getResultCount(),
                jsld.getCourtType(), jsld.getSignature(), jsld.getArticleNumber(), jsld.getReferencedRegulationYearPos(),
                jsld.getSearchPhrase());
    }

    public JudgmentSearchLogDto mapToJudgmentSearchLogDto(JudgmentSearchLog jsl) {
        return new JudgmentSearchLogDto(jsl.getId(), jsl.getCreatedOn(), jsl.getResponseTime(), jsl.getResultCount(),
                jsl.getCourtType(), jsl.getSignature(), jsl.getArticleNumber(), jsl.getReferencedRegulationYearPos(),
                jsl.getSearchPhrase());
    }
}
