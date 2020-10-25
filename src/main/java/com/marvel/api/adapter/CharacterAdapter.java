package com.marvel.api.adapter;

import com.marvel.api.dto.CharacterDTO;
import com.marvel.api.dto.ComicsDTO;
import com.marvel.api.entity.Character;
import com.marvel.api.form.CharacterForm;
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
                    .id(character.getId())
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

    public static List<ComicsDTO> fromCharacters(Character character) {
        if(Objects.isNull( character.getComics() )) {
            return new ArrayList<>();
        }
        return character.getComics()
                .stream()
                .map(
                        c->  ComicsDTO.builder()
                                .id(c.getId())
                                .name(c.getName())
                                .build())
                .collect(Collectors.toList());
    }
}
