package com.marvel.api.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.repository.data.sample.LoadSamples;
import com.marvel.api.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( CharacterController.class )
class CharacterControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    private static final String URL = "/public/v1/";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void givenValidRequestWhenSubmittedThenGetAllCharacters() throws Exception {

        when(characterService.getById(anyInt()))
                .thenReturn(LoadSamples.loadCharacter().get(0));

        mockMvc.perform(get(URL + "characters/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Hulk"))
                .andExpect(jsonPath("$.comics[0].id").value("1"))
                .andExpect(jsonPath("$.comics[0].name").value("Imortal Heroes"))
                .andExpect(jsonPath("$.comics[1].id").value("2"))
                .andExpect(jsonPath("$.comics[1].name").value("Juggernaut"))
                .andReturn();
    }
}
