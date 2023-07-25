package com.jornadamilhas.api.model.destination;


import com.jornadamilhas.api.model.infrastructure.StringValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Destination")
@Table(name = "destinations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Destination
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photoPath;
    private Float price;
    private boolean active;

    public Destination(DestinationPostData destinationPostData)
    {
        this.active = true;
        this.name = destinationPostData.name();
        this.photoPath = destinationPostData.photoPath();
        this.price = destinationPostData.price();
    }

    public void delete()
    {
        this.active = false;
    }

    public void dataUpdate(DestinationPutData destinationPutData)
    {
        if(!StringValidation.isNullEmptyOrBlank(destinationPutData.name()))
            this.name = destinationPutData.name();

        if(!StringValidation.isNullEmptyOrBlank(destinationPutData.photoPath()))
            this.photoPath = destinationPutData.photoPath();

        if(destinationPutData.price() != null)
            this.price = destinationPutData.price();
    }
}