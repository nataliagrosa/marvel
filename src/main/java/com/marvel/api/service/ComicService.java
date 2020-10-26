package com.marvel.api.service;

import com.marvel.api.entity.Comic;
import com.marvel.api.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ComicService {

    @Autowired
    ComicRepository comicRepository;

    private static final String RECORD_NOT_FOUND_MESSAGE = "Record not found";

    public Page<Comic> getAll(Pageable paginacao) {
        return comicRepository.findAll(paginacao);
    }

    public Comic getById(int id) {
        return comicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE));
    }

    public Comic save(Comic comic) {
        return comicRepository.save(comic);
    }

    public void delete(int id) {
        Optional<Comic> comics = comicRepository.findById(id);
        if ( comics.isPresent() ) {
            comicRepository.delete(comics.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE);
        }
    }
}
