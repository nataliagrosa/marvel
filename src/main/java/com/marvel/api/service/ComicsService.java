package com.marvel.api.service;

import com.marvel.api.entity.Comics;
import com.marvel.api.repository.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ComicsService {

    @Autowired
    ComicsRepository comicsRepository;

    private static final String RECORD_NOT_FOUND_MESSAGE = "Record not found";

    public Page<Comics> getAll(Pageable paginacao) {
        return comicsRepository.findAll(paginacao);
    }

    public Comics getById(int id) {
        return comicsRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE));
    }

    public Comics saveOrUpdate(Comics comics) {
        return comicsRepository.save(comics);
    }

    public void delete(int id) {
        Optional<Comics> comics = comicsRepository.findById(id);
        if ( comics.isPresent() ) {
            comicsRepository.delete(comics.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND_MESSAGE);
        }
    }
}
