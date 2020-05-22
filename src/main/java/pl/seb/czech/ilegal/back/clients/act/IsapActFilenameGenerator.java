package pl.seb.czech.ilegal.back.clients.act;

import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.back.clients.act.responses.IsapAct;


@Service
public class IsapActFilenameGenerator {
    private final String unifiedTextSuffix = "Lj";
    private final String extension = ".pdf";

    private String generateUnifiedTxtFilename(IsapAct isapActToGenerate) {
        String id = isapActToGenerate.getIsapId();
        char firstLetter = id.charAt(1);
        String year = id.substring(3, 7);
        String position = id.substring(id.length() - 4);
        return firstLetter + year + position + unifiedTextSuffix + extension;
    }

    private String generatePublishedTxtFileName(IsapAct isapActToGenerate) {
        return generateUnifiedTxtFilename(isapActToGenerate).replaceAll(unifiedTextSuffix, "");
    }
    
    public String generateTxtFilename(IsapAct actToGenerateFrom, IsapActTextType textType) {
        if(textType == IsapActTextType.UNIFIED) {
            return generateUnifiedTxtFilename(actToGenerateFrom);
        } else {
            return generatePublishedTxtFileName(actToGenerateFrom);
        }
    }
}
