package com.marvel.api.web.controller.dto;

import com.marvel.api.entity.Comic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTO {

    private int id;
    private String name;

    public ComicDTO(Comic comic) {
        this.id = comic.getId();
        this.name = comic.getName();
    }
}
