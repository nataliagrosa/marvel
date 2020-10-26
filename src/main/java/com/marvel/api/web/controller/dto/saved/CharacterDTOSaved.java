package com.marvel.api.web.controller.dto.saved;

import com.marvel.api.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.marvel.api.adapter.CharacterAdapter.fromCharactersForm;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTOSaved {

    private int id;
    private String name;
    private List<ComicDTOSaved> comics;

    public CharacterDTOSaved(Character character) {
        this.id = character.getId();
        this.name = character.getName();
        this.comics = fromCharactersForm(character);
    }
}
