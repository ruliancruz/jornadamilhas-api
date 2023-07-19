package com.jornadamilhas.api.model.testimonial;

public record TestimonialDetailedData(Long id, String personName, String testimonialText, String imagePath)
{
    public TestimonialDetailedData(Testimonial testimonial)
    {
        this(testimonial.getId(), testimonial.getPersonName(), testimonial.getTestimonialText(), testimonial.getImagePath());
    }
}