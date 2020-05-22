package pl.seb.czech.ilegal.back.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.client.act.responses.IsapAct;


@Service
public class ActDataProvider extends InitDataProvider<IsapAct> {
    private final static String[] FILE_NAMES = {"epidemy", "kc", "kk", "kpc", "kpk"};
    
    @Autowired
    public ActDataProvider(ObjectMapper objectMapper) {
        super(objectMapper, IsapAct.class, "acts", FILE_NAMES);
    }
}
