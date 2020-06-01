package pl.seb.czech.ilegal.back.controllers.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSearchLogDto;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentDeleteLogMapper;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentSearchLogMapper;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentDeleteLogDbService;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentSearchLogDbService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JudgmentHistoryControllerTest {
    @Value("${url.base.history}")
    private String baseEndpoint;
    @Value("${url.judgments.history.log.search}")
    private String searchLogEndpoint;
    @Value("${url.judgments.history.log.delete}")
    private String getDeleteLog;

    @Autowired
    MockMvc mvc;

    @MockBean
    private JudgmentSearchLogDbService searchLogDbService;
    @MockBean
    private JudgmentSearchLogMapper searchLogMapper;
    @MockBean
    private JudgmentDeleteLogDbService deleteLogDbService;
    @MockBean
    private JudgmentDeleteLogMapper deleteLogMapper;

    @Test
    void shouldGetSearchLog() throws Exception {
        List<JudgmentSearchLogDto> searchLogDto = new ArrayList<>();
        searchLogDto.add(new JudgmentSearchLogDto(-1L, NowTime.generate(), "abc", 5));
        searchLogDto.add(new JudgmentSearchLogDto());

        when(searchLogMapper.mapToDtoList(anyList())).thenReturn(searchLogDto);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + searchLogEndpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(searchLogDto.size())));
    }

    @Test
    void shouldClearSearchLog() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(baseEndpoint + searchLogEndpoint))
                .andDo(print())
                .andExpect(status().isOk());

        verify(searchLogDbService, atMostOnce()).deleteAll();
    }

    @Test
    void shouldGetDeleteLog() throws Exception {
        List<JudgmentDeleteLogDto> deleteLogDto = new ArrayList<>();
        deleteLogDto.add(new JudgmentDeleteLogDto());
        deleteLogDto.add(new JudgmentDeleteLogDto());

        when(deleteLogMapper.mapToDeleteLogDtoList(anyList())).thenReturn(deleteLogDto);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + getDeleteLog))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(deleteLogDto.size())));
    }
}