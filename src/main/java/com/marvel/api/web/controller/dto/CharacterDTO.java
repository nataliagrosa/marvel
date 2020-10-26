package com.marvel.api.web.controller.dto;

import com.marvel.api.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.marvel.api.adapter.CharacterAdapter.fromCharacters;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private int id;
    private String name;
    private List<ComicDTO> comics;

    public CharacterDTO(Character character) {
        this.id = character.getId();
        this.name = character.getName();
        this.comics = fromCharacters(character);
    }
}
