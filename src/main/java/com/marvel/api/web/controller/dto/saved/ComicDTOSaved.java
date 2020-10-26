package com.marvel.api.web.controller.dto.saved;

import com.marvel.api.entity.Comic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTOSaved {

    private int id;

    public ComicDTOSaved(Comic comic) {
        this.id = comic.getId();
    }
}
