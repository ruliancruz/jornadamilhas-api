package com.jornadamilhas.api.model.testimonial;

public record TestimonialGetData(Long id, String personName, String testimonialText, String imagePath)
{
    public TestimonialGetData(Testimonial testimonial)
    {
        this(testimonial.getId(), testimonial.getPersonName(), testimonial.getTestimonialText(), testimonial.getImagePath());
    }
}