package com.marvel.api.dto;

import com.marvel.api.entity.Comics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicsDTO {

    private int id;
    private String name;

    public ComicsDTO(Comics comics) {
        this.id = comics.getId();
        this.name = comics.getName();
    }
}
