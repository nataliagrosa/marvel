package com.marvel.api.repository.data.sample;

import com.marvel.api.entity.Character;
import com.marvel.api.entity.Comic;

import java.util.Arrays;
import java.util.List;

public class LoadSamples {

    public static List<Comic> loadComics(){
        return Arrays.asList(
                Comic.builder()
                        .id(1)
                        .name("Imortal Heroes")
                        .build(),
                Comic.builder()
                        .id(2)
                        .name("Juggernaut")
                        .build(),
                Comic.builder()
                        .id(3)
                        .name("The fantasy")
                        .build(),
                Comic.builder()
                        .id(4)
                        .name("World war")
                        .build()
        );
    }

    public static List<Character> loadCharacter(){

        List<Comic> hulkComics = Arrays.asList(
                    Comic.builder()
                            .id(1)
                            .name("Imortal Heroes")
                        .build(),
                    Comic.builder()
                            .id(2)
                            .name("Juggernaut")
                    .build());
        List<Comic> spiderComics = Arrays.asList(Comic.builder().id(3).build());

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
