package com.marvel.api.web.controller;

import com.marvel.api.web.controller.dto.CharacterDTO;
import com.marvel.api.web.controller.dto.ComicDTO;
import com.marvel.api.web.controller.form.CharacterForm;
import com.marvel.api.service.CharacterService;
import com.marvel.api.web.controller.dto.saved.CharacterDTOSaved;
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
import java.util.List;

import static com.marvel.api.adapter.CharacterAdapter.*;

@RestController
@RequestMapping("public/v1/characters")
public class CharacterController {
    
    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<Page<CharacterDTO>> getAll( @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable ) {
        return ResponseEntity.ok(fromEntitiesPage(characterService.getAll(pageable)));
    }

    @GetMapping("/{id}/comics")
    public ResponseEntity<List<ComicDTO>> getAllComicsByCharacterId(@PathVariable("id") int id, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable ) {
        return ResponseEntity.ok(fromCharacters(characterService.getAllComicsByCharacterId(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(fromEntity(characterService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<CharacterDTOSaved> save(@Valid @RequestBody CharacterForm character) {
        return ResponseEntity.ok(fromEntityForm(characterService.save(fromForm(character))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
