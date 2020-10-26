package com.marvel.api.adapter;

import com.marvel.api.web.controller.dto.CharacterDTO;
import com.marvel.api.web.controller.dto.saved.CharacterDTOSaved;
import com.marvel.api.web.controller.dto.ComicDTO;
import com.marvel.api.entity.Character;
import com.marvel.api.web.controller.dto.saved.ComicDTOSaved;
import com.marvel.api.web.controller.form.CharacterForm;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CharacterAdapter {

    public static Character fromForm ( CharacterForm character ) {
        return Character.builder()
                    .name(character.getName())
                    .comics(character.getComics())
                .build();
    }

    public static CharacterDTO fromEntity ( Character character ) {
        return CharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .comics(fromCharacters(character))
                .build();
    }

    public static Page<CharacterDTO> fromEntitiesPage(Page<Character> characters) {
        return characters.map(CharacterDTO::new);
    }

    public static List<ComicDTO> fromCharacters(Character character) {
        if(Objects.isNull( character.getComics() )) {
            return new ArrayList<>();
        }
        return character.getComics()
                .stream()
                .map(
                        c->  ComicDTO.builder()
                                .id(c.getId())
                                .name(c.getName())
                                .build())
                .collect(Collectors.toList());
    }

    public static CharacterDTOSaved fromEntityForm (Character character ) {
        return CharacterDTOSaved.builder()
                .id(character.getId())
                .name(character.getName())
                .comics(fromCharactersForm(character))
                .build();
    }

    public static List<ComicDTOSaved> fromCharactersForm(Character character) {
        if(Objects.isNull( character.getComics() )) {
            return new ArrayList<>();
        }
        return character.getComics()
                .stream()
                .map(
                        c->  ComicDTOSaved.builder()
                                .id(c.getId())
                                .build())
                .collect(Collectors.toList());
    }
}
