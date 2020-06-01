package pl.seb.czech.ilegal.back.clients.act;

import org.springframework.stereotype.Service;


@Service
public class IsapActFilenameGenerator {
    private final String unifiedTextSuffix = "Lj";
    private final String extension = ".pdf";

    private String generateUnifiedTxtFilename(String isapId) {
        char firstLetter = isapId.charAt(1);
        String year = isapId.substring(3, 7);
        String position = isapId.substring(isapId.length() - 4);
        return firstLetter + year + position + unifiedTextSuffix + extension;
    }

    private String generatePublishedTxtFileName(String isapId) {
        return generateUnifiedTxtFilename(isapId).replaceAll(unifiedTextSuffix, "");
    }
    
    public String generateTxtFilename(String isapId, IsapActTextType textType) {
        if(textType == IsapActTextType.UNIFIED) {
            return generateUnifiedTxtFilename(isapId);
        } else {
            return generatePublishedTxtFileName(isapId);
        }
    }
}
