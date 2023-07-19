package com.jornadamilhas.api.model.testimonial;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Testimonial")
@Table(name = "testimonials")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personName;
    private String testimonialText;
    private String imagePath;
    private boolean active;

    public Testimonial(TestimonialPostData testimonialPostData)
    {
        this.active = true;
        this.personName = testimonialPostData.personName();
        this.testimonialText = testimonialPostData.testimonialText();
        this.imagePath = testimonialPostData.imagePath();
    }

    public void delete()
    {
        this.active = false;
    }

    public void dataUpdate(TestimonialPutData testimonialPutData)
    {
        this.personName = testimonialPutData.personName();
        this.testimonialText = testimonialPutData.testimonialText();
        this.imagePath = testimonialPutData.imagePath();
    }
}