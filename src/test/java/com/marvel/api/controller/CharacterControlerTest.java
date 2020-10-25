package com.marvel.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.entity.Character;
import com.marvel.api.sample.CharacterSample;
import com.marvel.api.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( CharacterController.class )
class CharacterControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    private static final String URL = "/public/v1/";
    private static final String MOCK_ONE_CHARACTER = "src/test/resources/json/get-mock-character.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void givenValidRequestWhenSubmittedThenGetAllCharacters() throws Exception {

        when(characterService.getById(anyInt()))
                .thenReturn(CharacterSample.loadCharacter().get(0));

        mockMvc.perform(get(URL + "characters/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(MOCK_ONE_CHARACTER, Character.class)))
                .andReturn();
    }

    private String getJsonContent(String path, Class<?> clazz) throws IOException {
        return mapper.writeValueAsString(mapper.readValue(new File(path), clazz));
    }
}
