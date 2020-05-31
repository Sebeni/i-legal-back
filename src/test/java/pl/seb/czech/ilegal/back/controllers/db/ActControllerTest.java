package pl.seb.czech.ilegal.back.controllers.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.domain.act.ActPublisher;
import pl.seb.czech.ilegal.back.domain.act.dto.ActDto;
import pl.seb.czech.ilegal.back.domain.act.entity.Act;
import pl.seb.czech.ilegal.back.mappers.act.ActKeywordMapper;
import pl.seb.czech.ilegal.back.mappers.act.ActMapper;
import pl.seb.czech.ilegal.back.services.ElementNotFound;
import pl.seb.czech.ilegal.back.services.act.ActDbService;
import pl.seb.czech.ilegal.back.services.act.ActKeywordDbService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActController.class)
class ActControllerTest {
    @Value("${url.base.db}")
    private String baseEndpoint;
    @Value("${url.acts}")
    private String entityEndpoint;
    @Value("${url.acts.byIsapId}")
    private String entityByApiIdEndpoint;
    @Value("${url.acts.keywords}")
    private String keywordsEndpoint;
    
    private String entityIdVariable = "/{entityId}";
    
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;
    @MockBean
    private ActDbService dbService;
    @MockBean
    private ActMapper mapper;
    @MockBean
    private ActKeywordDbService keywordDbService;
    @MockBean
    private ActKeywordMapper keywordMapper;


    private static Act entity = new Act(-1L, "isapId", ActPublisher.WMP, -1, -2, -3, "title", "status", LocalDate.of(1, 1, 1), LocalDateTime.now(), "abc", "cde");
    private static List<Act> entityList = new ArrayList<>();

    private static ActDto dto = new ActDto(-1L, "isapId", ActPublisher.WMP, -1, -2, -3, "title", "status", LocalDate.of(1, 1, 1), LocalDateTime.now(), "abc", "cde");
    private static List<ActDto> dtoList = new ArrayList<>();

    @BeforeAll
    static void init() {
        entityList.add(entity);
        dtoList.add(dto);
    }

    @BeforeEach
    void initMock() {
        when(mapper.mapToDto(entity)).thenReturn(dto);
        when(mapper.mapToEntity(dto)).thenReturn(entity);

        when(mapper.mapToDtoList(any())).thenReturn(dtoList);
        when(mapper.mapToEntityList(any())).thenReturn(entityList);
    }

    @Test
    void shouldGetAllEntities() throws Exception {
        when(dbService.getAll()).thenReturn(entityList);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityEndpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldGetEntityById() throws Exception {
        when(dbService.getById(entity.getId())).thenReturn(entity);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityEndpoint + entityIdVariable, (entity.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId()));
    }

    @Test
    void shouldReturn404() throws Exception {
        when(dbService.getById(any())).thenThrow(ElementNotFound.class);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityEndpoint + entityIdVariable, -123L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCheckEntityByApiId() throws Exception {
        when(dbService.existsByIsapId(anyString())).thenReturn(false);
        when(dbService.existsByIsapId(entity.getIsapId())).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityByApiIdEndpoint + entityIdVariable, entity.getIsapId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityByApiIdEndpoint + entityIdVariable, "abc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    void shouldSaveEntity() throws Exception {
        when(dbService.save(any(Act.class))).thenReturn(entity);
        
        mvc.perform(MockMvcRequestBuilders.post(baseEndpoint + entityEndpoint)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId()));
        
        verify(dbService, atMostOnce()).save(entity);
    }

    @Test
    void shouldUpdateEntity() throws Exception {
        when(dbService.update(any(Act.class))).thenReturn(entity);
        
        mvc.perform(MockMvcRequestBuilders.put(baseEndpoint + entityEndpoint)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId()));
        
        verify(dbService, atMostOnce()).update(entity);
    }

    @Test
    void shouldDeleteEntity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(baseEndpoint + entityEndpoint + entityIdVariable, -123L))
                .andDo(print())
                .andExpect(status().isOk());
        
        verify(dbService, atMostOnce()).deleteById(-123L);
    }

    @Test
    void shouldDeleteAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(baseEndpoint + entityEndpoint))
                .andDo(print())
                .andExpect(status().isOk());

        verify(dbService, atMostOnce()).deleteAll();
    }

    @Test
    void shouldGetAllKeywords() throws Exception {
        List<String> keywords = Arrays.asList("abc", "bcd");
        when(keywordDbService.getAll()).thenReturn(new ArrayList<>());
        when(keywordMapper.mapToStringList(anyList())).thenReturn(keywords);
        
        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + keywordsEndpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(keywords.size())))
                .andExpect(jsonPath("$.[0]").value(keywords.get(0)))
                .andExpect(jsonPath("$.[1]").value(keywords.get(1)));
    }
}