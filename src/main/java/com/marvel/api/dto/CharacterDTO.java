package com.marvel.api.dto;

import com.marvel.api.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private int id;
    private String name;

    public CharacterDTO(Character character) {
        this.id = character.getId();
        this.name = character.getName();;
    }
}
