package pl.seb.czech.ilegal.back.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.client.judgment.responses.SaosJudgmentDetails;


@Service
public class JudgmentDetailsDataProvider extends InitDataProvider<SaosJudgmentDetails> {
    private final static String[] FILE_NAMES = {"commonDetails", "commonDetails2", "KIODetails", "SNDetails", "TKDetails"};

    @Autowired
    public JudgmentDetailsDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, SaosJudgmentDetails.class, "judgments/details",  FILE_NAMES);
    }
}
