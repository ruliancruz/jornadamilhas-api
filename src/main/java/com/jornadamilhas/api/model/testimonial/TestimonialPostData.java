package com.jornadamilhas.api.model.testimonial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TestimonialPostData
(
    @NotBlank
    @Size(max = 150, message = "{validation.personName.size.too_long}")
    String personName,
    @NotBlank
    @Size(max = 1000, message = "{validation.testimonialText.size.too_long}")
    String testimonialText,
    @NotBlank
    @Size(max = 255, message = "{validation.imagePath.size.too_long}")
    String photoPath
){}