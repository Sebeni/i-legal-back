package pl.seb.czech.ilegal.back.controllers.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.seb.czech.ilegal.back.domain.judgment.CourtType;
import pl.seb.czech.ilegal.back.domain.judgment.JudgmentType;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentDetailsDto;
import pl.seb.czech.ilegal.back.domain.judgment.dto.JudgmentSynopsisDto;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentDetails;
import pl.seb.czech.ilegal.back.domain.judgment.entity.JudgmentSynopsis;
import pl.seb.czech.ilegal.back.mappers.judgment.JudgmentMapper;
import pl.seb.czech.ilegal.back.services.ElementNotFound;
import pl.seb.czech.ilegal.back.services.judgment.JudgmentSynopsisDbService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JudgmentControllerTest {

    @Value("${url.base.db}")
    private String baseEndpoint;
    @Value("${url.judgments}")
    private String entityEndpoint;
    @Value("${url.judgments.bySaosId}")
    private String entityByApiIdEndpoint;

    private String entityIdVariable = "/{entityId}";

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mvc;
    @MockBean
    private JudgmentSynopsisDbService dbService;
    @MockBean
    private JudgmentMapper mapper;
    
    private static JudgmentSynopsis entity = new JudgmentSynopsis(-1L, -2L, CourtType.COMMON, new HashSet<>(), JudgmentType.DECISION, "custom", "synop", LocalDate.now(), new JudgmentDetails());
    private static List<JudgmentSynopsis> entityList = new ArrayList<>();

    private static JudgmentSynopsisDto dto = new JudgmentSynopsisDto(-1L, -2L, CourtType.COMMON, new HashSet<>(), JudgmentType.DECISION, "custom", "synop", LocalDate.now(), new JudgmentDetailsDto());
    private static List<JudgmentSynopsisDto> dtoList = new ArrayList<>();

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
        when(dbService.existsBySaosId(anyLong())).thenReturn(false);
        when(dbService.existsBySaosId(entity.getSaosId())).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityByApiIdEndpoint + entityIdVariable, entity.getSaosId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        mvc.perform(MockMvcRequestBuilders.get(baseEndpoint + entityByApiIdEndpoint + entityIdVariable, -123L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    void shouldSaveEntity() throws Exception {
        when(dbService.save(any(JudgmentSynopsis.class))).thenReturn(entity);

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
        when(dbService.update(any(JudgmentSynopsis.class))).thenReturn(entity);

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
}