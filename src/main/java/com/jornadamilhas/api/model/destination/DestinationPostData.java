package com.jornadamilhas.api.model.destination;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DestinationPostData
(
    @NotBlank
    @Size(max = 100, message = "{validation.personName.size.too_long}")
    String name,

    @NotBlank
    @Size(max = 255, message = "(validation.photoPath.size.too_long}")
    String photoPath,

    @NotNull
    Float price
){}
