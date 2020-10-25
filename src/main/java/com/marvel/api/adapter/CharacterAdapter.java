package com.marvel.api.adapter;

import com.marvel.api.dto.CharacterDTO;
import com.marvel.api.entity.Character;
import com.marvel.api.form.CharacterForm;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CharacterAdapter {

    public static Character fromForm ( CharacterForm character ) {
        return Character.builder()
                    .id(character.getId())
                    .name(character.getName())
                .build();
    }

    public static CharacterDTO fromEntity ( Character character ) {
        return CharacterDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .build();
    }

    public static Page<CharacterDTO> fromEntitiesPage(Page<Character> characters) {
        return characters.map(CharacterDTO::new);
    }

    public static List<CharacterDTO> fromEntities ( List<Character> characters ) {
        return characters
                .stream()
                .map(c -> CharacterDTO.builder()
                                .id(c.getId())
                                .name(c.getName())
                            .build())
                .collect(Collectors.toList());
    }
}
