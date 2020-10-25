package com.marvel.api.controller;


import com.marvel.api.dto.ComicsDTO;
import com.marvel.api.form.ComicsForm;
import com.marvel.api.service.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.marvel.api.adapter.ComicsAdapter.fromEntitiesPage;
import static com.marvel.api.adapter.ComicsAdapter.fromEntity;
import static com.marvel.api.adapter.ComicsAdapter.fromForm;

@RestController
@RequestMapping("public/v1/comics")
public class ComicsController {
    
    @Autowired
    ComicsService comicsService;

    @GetMapping
    public ResponseEntity<Page<ComicsDTO>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable ) {
        return ResponseEntity.ok(fromEntitiesPage(comicsService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicsDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(fromEntity(comicsService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ComicsDTO> save(@RequestBody @Valid ComicsForm comics) {
        return ResponseEntity.ok(fromEntity(comicsService.saveOrUpdate(fromForm(comics))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        comicsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
