package com.marvel.api.sample;

import com.marvel.api.entity.Character;
import com.marvel.api.entity.Comics;

import java.util.Arrays;
import java.util.List;

public class loadSamples {

    public static List<Comics> loadComics(){
        return Arrays.asList(
                Comics.builder()
                        .id(1)
                        .name("Imortal Heroes")
                        .build(),
                Comics.builder()
                        .id(2)
                        .name("Juggernaut")
                        .build(),
                Comics.builder()
                        .id(3)
                        .name("The fantasy")
                        .build(),
                Comics.builder()
                        .id(4)
                        .name("World war")
                        .build()
        );
    }

    public static List<Character> loadCharacter(){

        List<Comics> hulkComics = Arrays.asList(
                    Comics.builder()
                            .id(1)
                            .name("Imortal Heroes")
                        .build(),
                    Comics.builder()
                            .id(2)
                            .name("Juggernaut")
                    .build());
        List<Comics> spiderComics = Arrays.asList(Comics.builder().id(3).build());

        return Arrays.asList(
                Character.builder()
                    .id(1)
                    .name("Hulk")
                    .comics(hulkComics)
                .build(),
                Character.builder()
                        .id(2)
                        .name("Spider-Man")
                        .comics(spiderComics)
                    .build()
        );
    }
}
