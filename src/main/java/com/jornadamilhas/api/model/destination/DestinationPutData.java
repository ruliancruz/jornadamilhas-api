package com.jornadamilhas.api.model.destination;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record DestinationPutData
(
    @NotNull
    @PositiveOrZero
    Long id,

    @Size(max = 100, message = "{validation.personName.size.too_long}")
    String name,

    @Size(max = 255, message = "(validation.photoPath.size.too_long}")
    String photoPath,

    Float price
){}
