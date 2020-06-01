package pl.seb.czech.ilegal.back.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class InitDataProvider<E> {
    private List<E> convertedElements = new ArrayList<>();
    private static final String PATH_TO_EXAMPLES = "jsonExamples/";

    public InitDataProvider(ObjectMapper objectMapper, Class<E> elementType, String folderNameWithJsons, String... fileNames) {
        for (String jsonFileName : fileNames) {
            URL fileURL = this.getClass().getClassLoader().getResource( PATH_TO_EXAMPLES + folderNameWithJsons + "/" + jsonFileName + ".json");
            if(fileURL != null){
                try {
                    convertedElements.add(objectMapper.readValue(fileURL, elementType));
                } catch (IOException e) {
                    log.error("Exception in Data provider with type" + elementType.toString(), e);
                }
            }
        }
    }

    public List<E> getConvertedElements() {
        return convertedElements;
    }
}
