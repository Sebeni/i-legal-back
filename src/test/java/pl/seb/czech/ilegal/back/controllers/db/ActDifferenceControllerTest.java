package pl.seb.czech.ilegal.back.controllers.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.clients.act.ActDifferenceFinderFacade;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDifferenceDto;
import pl.seb.czech.ilegal.back.mappers.act.ActDifferenceMapper;
import pl.seb.czech.ilegal.back.scheduler.ScheduledMessageDto;
import pl.seb.czech.ilegal.back.scheduler.UpdateScheduler;
import pl.seb.czech.ilegal.back.services.act.ActDifferenceDbService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ActDifferenceControllerTest {
    @Value("${url.base}")
    private String baseEndpoint;
    @Value("${url.acts.difference.update}")
    private String updateEndpoint;
    @Value("${url.acts.difference.scheduled}")
    private String scheduledMessageEndpoint;
    @Value("${url.acts.difference}")
    private String diffHistory;

    @Autowired
    MockMvc mvc;
    
    @MockBean
    private ActDifferenceFinderFacade actDifferenceFinderFacade;
    @MockBean
    private ActDifferenceDbService dbService;
    @MockBean
    private ActDifferenceMapper actDifferenceMapper;
    @MockBean
    private UpdateScheduler updateScheduler;
    
    @Test
    void shouldUpdateActs() throws Exception {
        List<ActDifferenceDto> result = new ArrayList<>(Arrays.asList(new ActDifferenceDto(), new ActDifferenceDto()));
        when(actDifferenceFinderFacade.getActDifferences()).thenReturn(result);
        
        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + updateEndpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(result.size())));
    }

    @Test
    void shouldGetScheduledInfo() throws Exception {
        ScheduledMessageDto message = new ScheduledMessageDto();
        when(updateScheduler.getScheduledMessageDto()).thenReturn(message);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + scheduledMessageEndpoint))
                .andDo(print())
                .andExpect(status().isOk());
        
        verify(updateScheduler, atMostOnce()).getScheduledMessageDto();
    }

    @Test
    void shouldGetDiffHistory() throws Exception {
        List<ActDifferenceDto> result = new ArrayList<>(Arrays.asList(new ActDifferenceDto(), new ActDifferenceDto()));
        when(actDifferenceMapper.mapToDtoList(anyList())).thenReturn(result);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + diffHistory))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(result.size())));
    }

    @Test
    void shouldDeleteAllHistory() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete(baseEndpoint + diffHistory))
                .andDo(print())
                .andExpect(status().isOk());
        
        verify(dbService, atMostOnce()).deleteAll();
    }
}