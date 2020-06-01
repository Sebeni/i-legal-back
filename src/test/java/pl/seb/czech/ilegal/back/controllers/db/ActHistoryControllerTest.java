package pl.seb.czech.ilegal.back.controllers.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.clients.act.ActDifferenceFinderFacade;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDeleteLogDto;
import pl.seb.czech.ilegal.back.domain.act.dto.ActSearchLogDto;
import pl.seb.czech.ilegal.back.mappers.act.ActDeleteLogMapper;
import pl.seb.czech.ilegal.back.mappers.act.ActDifferenceMapper;
import pl.seb.czech.ilegal.back.mappers.act.ActSearchLogMapper;
import pl.seb.czech.ilegal.back.scheduler.UpdateScheduler;
import pl.seb.czech.ilegal.back.services.act.ActDeleteLogDbService;
import pl.seb.czech.ilegal.back.services.act.ActDifferenceDbService;
import pl.seb.czech.ilegal.back.services.act.ActSearchLogDbService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ActHistoryControllerTest {
    @Value("${url.base.history}")
    private String baseEndpoint;
    @Value("${url.acts.history.log.search}")
    private String searchLogEndpoint;
    @Value("${url.acts.history.log.delete}")
    private String getDeleteLog;
    
    @Autowired
    MockMvc mvc;

    @MockBean
    private ActSearchLogDbService searchLogDbService;
    @MockBean
    private ActSearchLogMapper searchLogMapper;
    @MockBean
    private ActDeleteLogDbService deleteLogDbService;
    @MockBean
    private ActDeleteLogMapper deleteLogMapper;
    
    @Test
    void shouldGetSearchLog() throws Exception {
        List<ActSearchLogDto> searchLogDto = new ArrayList<>();
        searchLogDto.add(new ActSearchLogDto(-1L, NowTime.generate(), "abc", 5));
        searchLogDto.add(new ActSearchLogDto());
        
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
        List<ActDeleteLogDto> deleteLogDto = new ArrayList<>();
        deleteLogDto.add(new ActDeleteLogDto());
        deleteLogDto.add(new ActDeleteLogDto());
        
        when(deleteLogMapper.mapToDeleteLogDtoList(anyList())).thenReturn(deleteLogDto);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + getDeleteLog))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(deleteLogDto.size())));
    }
}