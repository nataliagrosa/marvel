package com.marvel.api.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.entity.Comic;
import com.marvel.api.repository.data.sample.LoadSamples;
import com.marvel.api.service.ComicService;
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

@WebMvcTest( ComicController.class )
class ComicControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComicService comicService;

    private static final String URL = "/public/v1/";
    private static final String MOCK_ONE_COMIC = "src/test/resources/json/get-mock-comic.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void givenValidRequestWhenSubmittedThenGetAllComics() throws Exception {

        when(comicService.getById(anyInt()))
                .thenReturn(LoadSamples.loadComics().get(0));

        mockMvc.perform(get(URL + "comics/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(MOCK_ONE_COMIC, Comic.class)))
                .andReturn();
    }

    private String getJsonContent(String path, Class<?> clazz) throws IOException {
        return mapper.writeValueAsString(mapper.readValue(new File(path), clazz));
    }
}
