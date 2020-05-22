package pl.seb.czech.ilegal.back.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.client.judgment.responses.SaosJudgmentSynopsis;

@Service
public class JudgmentSynopsisDataProvider extends InitDataProvider<SaosJudgmentSynopsis> {
    private static final String[] FILE_NAMES = { "commonSynopsis", "commonSynopsis2", "KIOSynopsis", "SNSynopsis", "TKSynopsis"};
    
    
    @Autowired
    public JudgmentSynopsisDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, SaosJudgmentSynopsis.class, "judgments/synopsis", FILE_NAMES);
    }
}
