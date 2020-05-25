package pl.seb.czech.ilegal.back.mappers.judgment;

import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.judgment.dto.*;
import pl.seb.czech.ilegal.back.domain.judgment.entity.*;
import pl.seb.czech.ilegal.back.mappers.ListMapper;

import java.util.stream.Collectors;

@Component
public class JudgmentMapper extends ListMapper<JudgmentSynopsis, JudgmentSynopsisDto> {

    @Override
    public JudgmentSynopsis mapToEntity(JudgmentSynopsisDto jsd) {
        return new JudgmentSynopsis(jsd.getId(), jsd.getSaosId(), jsd.getCourtType(), jsd.getCaseNumbers(),
                jsd.getJudgmentType(), jsd.getCustomName(), jsd.getSynopsis(), jsd.getJudgmentDate(),
                mapToJudgmentDetails(jsd.getJudgmentDetails()));
    }

    @Override
    public JudgmentSynopsisDto mapToDto(JudgmentSynopsis js) {
        return new JudgmentSynopsisDto(js.getId(), js.getSaosId(), js.getCourtType(), js.getCaseNumbers(),
                js.getJudgmentType(), js.getCustomName(), js.getSynopsis(), js.getJudgmentDate(),
                mapToJudgmentDetailsDto(js.getJudgmentDetails()));
    }

    public ReferencedRegulation mapToReferencedRegulation(ReferencedRegulationDto rrd) {
        if (rrd != null) {
            return new ReferencedRegulation(rrd.getTitle(), rrd.getYear(), rrd.getVolume(),
                    rrd.getPosition(), rrd.getText());
        } else {
            return null;
        }
    }

    public ReferencedRegulationDto mapToReferencedRegulationDto(ReferencedRegulation rr) {
        if (rr != null) {
            return new ReferencedRegulationDto(rr.getTitle(), rr.getYear(), rr.getVolume(),
                    rr.getPosition(), rr.getText());
        } else {
            return null;
        }
    }

    public JudgmentKeyword mapToJudgmentKeyword(JudgmentKeywordDto jkd) {
        if (jkd != null) {
            return new JudgmentKeyword(jkd.getName());
        } else {
            return null;
        }
    }

    public JudgmentKeywordDto mapToJudgmentKeywordDto(JudgmentKeyword jk) {
        if (jk != null) {
            return new JudgmentKeywordDto(jk.getName());
        } else {
            return null;
        }
    }

    public JudgmentDetails mapToJudgmentDetails(JudgmentDetailsDto jdd) {
        if (jdd != null) {
            return new JudgmentDetails(jdd.getId(), jdd.getSaosId(), jdd.getTextContent(),
                    jdd.getReferencedRegulations().stream().map(this::mapToReferencedRegulation).collect(Collectors.toSet()),
                    jdd.getKeywords().stream().map(this::mapToJudgmentKeyword).collect(Collectors.toSet())
            );
        } else {
            return null;
        }
    }

    public JudgmentDetailsDto mapToJudgmentDetailsDto(JudgmentDetails jd) {
        if (jd != null) {
            return new JudgmentDetailsDto(jd.getId(), jd.getSaosId(), jd.getTextContent(),
                    jd.getReferencedRegulations().stream().map(this::mapToReferencedRegulationDto).collect(Collectors.toSet()),
                    jd.getKeywords().stream().map(this::mapToJudgmentKeywordDto).collect(Collectors.toSet()));
        } else {
            return null;
        }

    }
}
