package com.jornadamilhas.api.model.testimonial;

import com.jornadamilhas.api.model.infrastructure.StringValidation;
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
    private String photoPath;
    private boolean active;

    public Testimonial(TestimonialPostData testimonialPostData)
    {
        this.active = true;
        this.personName = testimonialPostData.personName();
        this.testimonialText = testimonialPostData.testimonialText();
        this.photoPath = testimonialPostData.photoPath();
    }

    public void delete()
    {
        this.active = false;
    }

    public void dataUpdate(TestimonialPutData testimonialPutData)
    {
        if(!StringValidation.isNullEmptyOrBlank(testimonialPutData.personName()))
            this.personName = testimonialPutData.personName();

        if(!StringValidation.isNullEmptyOrBlank(testimonialPutData.testimonialText()))
            this.testimonialText = testimonialPutData.testimonialText();

        if(!StringValidation.isNullEmptyOrBlank(testimonialPutData.photoPath()))
            this.photoPath = testimonialPutData.photoPath();
    }
}