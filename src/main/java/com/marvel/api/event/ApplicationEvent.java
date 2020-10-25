package com.marvel.api.event;

import com.marvel.api.repository.CharacterRepository;
import com.marvel.api.repository.ComicsRepository;
import com.marvel.api.sample.loadSamples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ComicsRepository comicsRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadSamples.loadComics()
                .stream()
                .forEach(c ->
                        comicsRepository.save(c));
        loadSamples.loadCharacter()
                .stream()
                    .forEach(c ->
                        characterRepository.save(c)
                    );
    }
}