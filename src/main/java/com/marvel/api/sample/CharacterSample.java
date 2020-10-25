package com.marvel.api.sample;

import com.marvel.api.entity.Character;

import java.util.Arrays;
import java.util.List;

public class CharacterSample {
    public static List<Character> loadCharacter(){
        return Arrays.asList(
                Character.builder()
                    .id(1)
                    .name("Hulk")
                .build(),
                Character.builder()
                        .id(2)
                        .name("Spider-Man")
                        .build()
        );
    }
}
