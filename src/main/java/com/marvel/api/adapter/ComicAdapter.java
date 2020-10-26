package com.marvel.api.adapter;

import com.marvel.api.web.controller.dto.ComicDTO;
import com.marvel.api.entity.Comic;
import com.marvel.api.web.controller.form.ComicForm;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
public class ComicAdapter {

    public static Comic fromForm(ComicForm comic) {
        return Comic.builder()
                .name(comic.getName())
                .build();
    }

    public static ComicDTO fromEntity(Comic comic) {
        return ComicDTO.builder()
                .id(comic.getId())
                .name(comic.getName())
                .build();
    }

    public static Page<ComicDTO> fromEntitiesPage(Page<Comic> comics) {
        return comics.map(ComicDTO::new);
    }
}
