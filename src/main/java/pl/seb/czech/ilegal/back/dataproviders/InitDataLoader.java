package pl.seb.czech.ilegal.back.dataproviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.domain.ServerStartLog;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisDto;
import pl.seb.czech.ilegal.back.mappers.act.ActMapper;
import pl.seb.czech.ilegal.back.mappers.act.IsapMapper;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentMapper;
import pl.seb.czech.ilegal.back.mappers.judgment.SaosMapper;
import pl.seb.czech.ilegal.back.repositories.ServerStartRepository;
import pl.seb.czech.ilegal.back.services.act.ActDbService;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentSynopsisDbService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitDataLoader implements CommandLineRunner {
    @Autowired
    ServerStartRepository serverStartRepository;
    
    @Autowired
    private ActDataProvider actDataProvider;
    @Autowired
    private ActDbService actDbService;

    @Autowired
    private JudgmentSynopsisDP judgmentSynopsisDP;
    @Autowired
    private JudgmentDetailsDP judgmentDetailsDP;
    @Autowired
    private JudgmentSynopsisDbService judgmentDbService;

    @Autowired
    private JudgmentMapper judgmentMapper;
    @Autowired
    private SaosMapper saosMapper;
    @Autowired
    private ActMapper actMapper;
    @Autowired
    private IsapMapper isapMapper;
    
    @Override
    public void run(String... args) throws Exception {
        serverStartRepository.save(new ServerStartLog());
        
        List<ActDto> actDtos = isapMapper.mapToDtoList(actDataProvider.getConvertedElements());
        actDtos.forEach(actDto -> actDbService.save(actMapper.mapToEntity(actDto)));
        
        List<JudgmentSynopsisDto> judgmentSynopsisDtos = saosMapper.mapToDtoList(judgmentSynopsisDP.getConvertedElements());
        List<JudgmentDetailsDto> judgmentDetailsDtos = judgmentDetailsDP.getConvertedElements().stream()
                .map(saosJudgmentDetails -> saosMapper.mapToJudgmentDetailDto(saosJudgmentDetails))
                .collect(Collectors.toList());
        
        judgmentSynopsisDtos.forEach(judgmentSynopsisDto -> {
            pairSynopsisToDetails(judgmentSynopsisDto, judgmentDetailsDtos);
            judgmentDbService.save(judgmentMapper.mapToEntity(judgmentSynopsisDto));
        });
        
    }
    
    private void pairSynopsisToDetails(JudgmentSynopsisDto synopsis, List<JudgmentDetailsDto> judgmentDetailsDtos) {
        JudgmentDetailsDto correctDetail = judgmentDetailsDtos.stream()
                .filter(judgmentDetailsDto -> judgmentDetailsDto.getSaosId().equals(synopsis.getSaosId()))
                .findFirst()
                .orElse(null);
        synopsis.setJudgmentDetails(correctDetail);
    }
}
