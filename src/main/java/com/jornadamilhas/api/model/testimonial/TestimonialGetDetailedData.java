package com.jornadamilhas.api.model.testimonial;

public record TestimonialGetDetailedData(Long id, String personName, String testimonialText, String imagePath)
{
    public TestimonialGetDetailedData(Testimonial testimonial)
    {
        this(testimonial.getId(), testimonial.getPersonName(), testimonial.getTestimonialText(), testimonial.getImagePath());
    }
}