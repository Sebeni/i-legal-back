package pl.seb.czech.ilegal.back.dataproviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.seb.czech.ilegal.back.repositories.act.ActRepository;
import pl.seb.czech.ilegal.back.repositories.judgment.JudgmentSynopsisRepository;

@Component
public class InitDataLoader implements CommandLineRunner {
    private ActDataProvider actDataProvider;
    private ActRepository actRepository;
    private JudgmentSynopsisDataProvider judgmentSynopsisDataProvider;
    private JudgmentSynopsisRepository judgmentSynopsisRepository;
    
    private JudgmentDetailsDataProvider judgmentDetailsDataProvider;
    

    @Autowired
    public InitDataLoader(ActDataProvider actDataProvider, ActRepository actRepository, JudgmentSynopsisDataProvider judgmentSynopsisDataProvider, JudgmentDetailsDataProvider judgmentDetailsDataProvider, JudgmentSynopsisRepository judgmentSynopsisRepository) {
        this.actDataProvider = actDataProvider;
        this.actRepository = actRepository;
        this.judgmentSynopsisDataProvider = judgmentSynopsisDataProvider;
        this.judgmentDetailsDataProvider = judgmentDetailsDataProvider;
        this.judgmentSynopsisRepository = judgmentSynopsisRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        actRepository.saveAll(actDataProvider.getConvertedElements());
//        judgmentSynopsisRepository.saveAll(judgmentSynopsisDataProvider.getConvertedElements());
//        
//        judgmentDetailsDataProvider.getConvertedElements().forEach(judgmentDetails -> {
//            Optional<JudgmentSynopsis> optional = judgmentSynopsisRepository.findBySaosId(judgmentDetails.getSaosId());
//            if(optional.isPresent()){
//                JudgmentSynopsis fromRepo = optional.get();
//                fromRepo.setJudgmentDetails(judgmentDetails);
//                judgmentSynopsisRepository.save(fromRepo);
//            }
//        });
    }
}
