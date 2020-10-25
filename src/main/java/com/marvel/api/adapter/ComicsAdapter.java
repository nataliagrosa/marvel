package com.marvel.api.adapter;

import com.marvel.api.dto.ComicsDTO;
import com.marvel.api.entity.Comics;
import com.marvel.api.form.ComicsForm;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
public class ComicsAdapter {

    public static Comics fromForm(ComicsForm comics) {
        return Comics.builder()
                .id(comics.getId())
                .name(comics.getName())
                .build();
    }

    public static ComicsDTO fromEntity(Comics comics) {
        return ComicsDTO.builder()
                .id(comics.getId())
                .name(comics.getName())
                .build();
    }

    public static Page<ComicsDTO> fromEntitiesPage(Page<Comics> comics) {
        return comics.map(ComicsDTO::new);
    }
}
