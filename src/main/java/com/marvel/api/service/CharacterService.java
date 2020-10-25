package com.marvel.api.service;

import com.marvel.api.entity.Character;
import com.marvel.api.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    private static final String RECORD_NOT_FOUND_MESSAGE = "Record not found";

    public Page<Character> getAll(Pageable paginacao) {
        return characterRepository.findAll(paginacao);
    }

    public Character getById(int id) {
        return characterRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE));
    }

    public Character saveOrUpdate(Character character) {
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
