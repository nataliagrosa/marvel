package com.marvel.api.web.controller.form;

import com.marvel.api.entity.Comic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterForm {

    @NotNull(message = "{field.name.required}")
    @NotBlank(message = "{field.name.not.be.empty}")
    private String name;
    private List<Comic> comics;
}