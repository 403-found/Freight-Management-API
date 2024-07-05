package com.springrest.springrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.controller.MyController;
import com.springrest.springrest.entities.Load;
import com.springrest.springrest.services.LoadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MyController.class)
class SpringrestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoadService loadService;

    @Test
    void testGetLoads() throws Exception {
        Load load1 = new Load("Delhi", "Jaipur", "Chemicals", "Canter", 1, 100, "Should reach", 16, "26-08-2022");
        Load load2 = new Load("Goa", "Jaipur", "Soap", "Tow Truck", 2, 120, "Can reach", 77, "14-09-2022");

        when(loadService.getLoad()).thenReturn(Arrays.asList(load1, load2));

        mockMvc.perform(get("/load"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].loadingPoint").value("Delhi"))
                .andExpect(jsonPath("$[1].loadingPoint").value("Goa"));
    }

    @Test
    void testGetLoadById() throws Exception {
        Load load = new Load("Delhi", "Jaipur", "Chemicals", "Canter", 1, 100, "Should reach", 16, "26-08-2022");

        when(loadService.getLoads(anyLong())).thenReturn(load);

        mockMvc.perform(get("/load/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loadingPoint").value("Delhi"));
    }

    @Test
    void testAddLoad() throws Exception {
        Load load = new Load("Mumbai", "Pune", "Electronics", "Trailer", 1, 100, "Handle with care", 100, "01-07-2023");

        when(loadService.addLoad(any(Load.class))).thenReturn(load);

        mockMvc.perform(post("/load")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(load)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loadingPoint").value("Mumbai"));
    }

    @Test
    void testUpdateLoad() throws Exception {
        Load load = new Load("Mumbai", "Pune", "Electronics", "Trailer", 1, 100, "Handle with care", 100, "01-07-2023");

        when(loadService.updateLoad(null, any(Load.class))).thenReturn(load);

        mockMvc.perform(put("/load/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(load)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.loadingPoint").value("Mumbai"));
    }

    @Test
    void testDeleteLoad() throws Exception {
        mockMvc.perform(delete("/load/1"))
                .andExpect(status().isOk());
    }
}
