package com.marvel.api.service;

import com.marvel.api.entity.Character;
import com.marvel.api.repository.CharacterRepository;
import com.marvel.api.repository.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    ComicsRepository comicsRepository;

    private static final String RECORD_NOT_FOUND_MESSAGE = "Record not found";

    public Page<Character> getAll(Pageable pageable) {
        return characterRepository.findAll(pageable);
    }

    public Character getById(int id) {
        return characterRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE));
    }

    public Character getAllComicsByCharacterId(int id){
        Optional<Character> character = characterRepository.findById(id);
        if ( !character.isPresent() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE);
        }
        return character.get();
    }

    public Character saveOrUpdate(Character character) {
        if(Objects.nonNull(character.getComics())) {
            character.getComics()
                    .stream().forEach(c -> {
                Optional<Character> optionalCharacter = characterRepository.findById(c.getId());
                if ( !optionalCharacter.isPresent() ) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE);
                }
            });
        }
        return characterRepository.save(character);
    }

    public void delete(int id) {
        Optional<Character> character = characterRepository.findById(id);
        if ( character.isPresent() ) {
            characterRepository.delete(character.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE);
        }
    }
}
