package pl.seb.czech.ilegal.back.controllers.db;

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
import pl.seb.czech.ilegal.back.domain.ChangeViewLog;
import pl.seb.czech.ilegal.back.domain.ChangeViewLogDto;
import pl.seb.czech.ilegal.back.domain.NowTime;
import pl.seb.czech.ilegal.back.mappers.ChangeViewLogMapper;
import pl.seb.czech.ilegal.back.services.ChangeViewLogDbService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChangeViewLogControllerTest {
    @Value("${url.base.history}")
    private String baseEndpoint;

    @Value("${url.history.changeview}")
    private String saveEndpoint;

    @MockBean
    private ChangeViewLogDbService dbService;
    @MockBean
    private ChangeViewLogMapper mapper;
    
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldSaveEntity() throws Exception {
        ChangeViewLogDto dto = new ChangeViewLogDto(-5L, "view", NowTime.generate());
        ChangeViewLog entity = new ChangeViewLog(-5L, "view", NowTime.generate());
        when(mapper.mapToDto(any(ChangeViewLog.class))).thenReturn(dto);
        when(mapper.mapToEntity(any(ChangeViewLogDto.class))).thenReturn(entity);
        when(dbService.save(any(ChangeViewLog.class))).thenReturn(entity);
        
        mvc.perform(MockMvcRequestBuilders.post(baseEndpoint + saveEndpoint)  
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.viewName").value(dto.getViewName()));
        
        verify(dbService, atMostOnce()).save(entity);
    }
}