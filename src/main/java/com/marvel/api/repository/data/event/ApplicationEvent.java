package com.marvel.api.repository.data.event;

import com.marvel.api.repository.CharacterRepository;
import com.marvel.api.repository.ComicRepository;
import com.marvel.api.repository.data.sample.LoadSamples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LoadSamples.loadComics()
                .stream()
                .forEach(c ->
                        comicRepository.save(c));
        LoadSamples.loadCharacter()
                .stream()
                    .forEach(c ->
                        characterRepository.save(c)
                    );
    }
}