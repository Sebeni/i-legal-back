package pl.seb.czech.ilegal.back.controllers.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.clients.judgment.SaosClient;
import pl.seb.czech.ilegal.back.clients.judgment.responses.SaosJudgmentDetails;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchQueryDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisSearchResultDto;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentSearchQueryMapper;
import pl.seb.czech.ilegal.back.mappers.judgment.SaosMapper;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaosController.class)
class SaosControllerTest {
    @Value("${url.base.search}")
    private String baseEndpoint;
    @Value("${url.judgments.saos}")
    private String performSearchEndpoint;
    @Value("${url.judgments.saos.details}")
    private String getDetailsEndpoint;

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private SaosMapper saosMapper;
    @MockBean
    private SaosClient saosClient;
    @MockBean
    private JudgmentSearchQueryMapper searchMapper;

    @Test
    void shouldPerformSearch() throws Exception {
        JudgmentSynopsisSearchResultDto dto = new JudgmentSynopsisSearchResultDto(new ArrayList<>(), -5, -1);
        when(saosMapper.mapToJudgmentSynopsisSearchResultDto(any())).thenReturn(dto);

        mvc.perform(MockMvcRequestBuilders.post(baseEndpoint + performSearchEndpoint)
                .content(objectMapper.writeValueAsString(new JudgmentSearchQueryDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results", hasSize(dto.getResults().size())))
                .andExpect(jsonPath("$.numOfResults", is(dto.getNumOfResults())))
                .andExpect(jsonPath("$.pageNumber", is(dto.getPageNumber())));
    }

    @Test
    void shouldSearchForJudgmentDetails() throws Exception {
        JudgmentDetailsDto dto = new JudgmentDetailsDto(-5L, -6L, "content", null, new HashSet<>(), new HashSet<>());
        when(saosMapper.mapToJudgmentDetailDto(any())).thenReturn(dto);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + getDetailsEndpoint + "/{saosId}", -6L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.saosId").value(dto.getSaosId()))
                .andExpect(jsonPath("$.textContent", is(dto.getTextContent())));
    }
}