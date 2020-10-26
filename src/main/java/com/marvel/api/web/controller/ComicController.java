package com.marvel.api.web.controller;


import com.marvel.api.web.controller.dto.ComicDTO;
import com.marvel.api.web.controller.form.ComicForm;
import com.marvel.api.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.marvel.api.adapter.ComicAdapter.fromEntitiesPage;
import static com.marvel.api.adapter.ComicAdapter.fromEntity;
import static com.marvel.api.adapter.ComicAdapter.fromForm;

@RestController
@RequestMapping("public/v1/comics")
public class ComicController {
    
    @Autowired
    ComicService comicService;

    @GetMapping
    public ResponseEntity<Page<ComicDTO>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable ) {
        return ResponseEntity.ok(fromEntitiesPage(comicService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(fromEntity(comicService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ComicDTO> save(@RequestBody @Valid ComicForm comics) {
        return ResponseEntity.ok(fromEntity(comicService.save(fromForm(comics))));
    }
}
