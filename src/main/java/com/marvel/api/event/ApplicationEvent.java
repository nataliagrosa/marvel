package com.marvel.api.event;

import com.marvel.api.repository.CharacterRepository;
import com.marvel.api.sample.CharacterSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        CharacterSample.loadCharacter()
                .stream()
                    .forEach(c ->
                        characterRepository.save(c)
                    );
    }
}