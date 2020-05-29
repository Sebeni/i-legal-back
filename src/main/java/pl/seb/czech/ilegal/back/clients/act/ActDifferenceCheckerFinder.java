package pl.seb.czech.ilegal.back.clients.act;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapActSearchResult;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.mappers.act.ActDifferenceMapper;
import pl.seb.czech.ilegal.back.mappers.act.ActMapper;
import pl.seb.czech.ilegal.back.services.act.ActDbService;
import pl.seb.czech.ilegal.back.services.act.ActDifferenceDbService;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ActDifferenceCheckerFinder implements ActDifferenceFinderFacade {
    private final ActDifferenceDbService actDiffDb;
    private final ActDbService actDb;
    private final IsapClient isapClient;
    private final ActMapper actMapper;
    private final ActDifferenceMapper actDifferenceMapper;

    @Autowired
    public ActDifferenceCheckerFinder(ActDifferenceDbService actDiffDb, ActDbService actDb, IsapClient isapClient,
                                      ActMapper actMapper, ActDifferenceMapper actDifferenceMapper) {
        this.actDiffDb = actDiffDb;
        this.actDb = actDb;
        this.isapClient = isapClient;
        this.actMapper = actMapper;
        this.actDifferenceMapper = actDifferenceMapper;
  
    }

    @Override
    public List<ActDifferenceDto> getActDifferences() {
        log.info("Performing check for act update for all acts in DB");
        List<ActDifferenceDto> result = new ArrayList<>();
        
        List<Act> actsFromDb = actDb.getAll();

        actsFromDb.forEach(actDb -> {
            ActDto actFromDb = actMapper.mapToDto(actDb);
            IsapActSearchResult resultQuery = isapClient.performSearchQuery(createQuery(actFromDb));
            if(resultQuery.getNumOfResults() > 0) {
                IsapAct actFromQuery = resultQuery.getResultsList().get(0);
                ActDifferenceDto difference = new ActDifferenceDto(actFromDb, actFromQuery);
                result.add(difference);
                
                if(difference.isDifferentAfter()){
                    updateAct(actFromDb, actFromQuery);
                }
            } else {
                log.error("Did not found act with isap id: " + actFromDb.getIsapId() + 
                        " and domain id: " + actDb.getId());
            }
        });
        
        return actDifferenceMapper.mapToDtoList(actDiffDb.saveAll(actDifferenceMapper.mapToEntityList(result)));
    }
    
    private IsapActSearchQuery createQuery(ActDto actDto) {
        return new IsapActSearchQuery(null, actDto.getTitle(), null, null, 
                actDto.getPublisher(), actDto.getYear(), actDto.getPosition(), 0);
    }
    
    private void updateAct(ActDto actFromDb, IsapAct actFromQuery) {
        log.info("Updating act after check. Act id: " + actFromDb.getId());
        actFromDb.setStatus(actFromQuery.getStatus());
        actFromDb.setChangeDate(actFromQuery.getChangeDate());
        actDb.save(actMapper.mapToEntity(actFromDb));
    }
}
