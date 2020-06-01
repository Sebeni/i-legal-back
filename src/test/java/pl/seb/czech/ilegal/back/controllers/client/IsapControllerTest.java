package pl.seb.czech.ilegal.back.controllers.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.clients.act.IsapActTextType;
import pl.seb.czech.ilegal.back.clients.act.IsapClient;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchQueryDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.act.ActSearchQueryMapper;
import pl.seb.czech.ilegal.back.mappers.act.IsapMapper;

import java.net.URI;
import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IsapControllerTest {
    @Value("${url.base.search}")
    private String baseEndpoint;
    @Value("${url.acts.isap}")
    private String performSearchEndpoint;
    @Value("${url.acts.isap.text.link}")
    private String getUriEndpoint;
    @Value("${url.acts.isap.text.link.check}")
    private String checkTxtEndpoint;
    
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mvc;
    @MockBean
    private IsapClient client;
    @MockBean
    private IsapMapper mapper;
    @MockBean
    private ActSearchQueryMapper queryMapper;
    
    ActSearchResultDto dto = new ActSearchResultDto(new ArrayList<>(), 5, 6, 7);
    
    @Test
    void shouldPerformSearch() throws Exception {
        when(mapper.mapToActSearchResultDto(any())).thenReturn(dto);
        
        mvc.perform(MockMvcRequestBuilders.post(baseEndpoint + performSearchEndpoint)
                .content(objectMapper.writeValueAsString(new ActSearchQueryDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foundActs", hasSize(dto.getFoundActs().size())))
                .andExpect(jsonPath("$.offset", is(dto.getOffset())))
                .andExpect(jsonPath("$.count", is(dto.getCount())))
                .andExpect(jsonPath("$.numOfResults", is(dto.getNumOfResults())));
    }

    @Test
    void shouldGetTxtDownloadUri() throws Exception {
        when(client.generateDownloadActURI(anyString(), any(IsapActTextType.class))).thenReturn(new URI("test"));

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + getUriEndpoint + "/{isapId}" + "/{textType}", "isapId", IsapActTextType.PUBLISHED))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("test")));
    }

    @Test
    void shouldCheckIfTextExists() throws Exception {
        when(client.validateTxtExists(anyString())).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + checkTxtEndpoint).queryParam("uri", "sometest"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }
}