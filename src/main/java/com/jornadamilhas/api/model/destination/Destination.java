package com.jornadamilhas.api.model.destination;


import com.jornadamilhas.api.model.infrastructure.StringValidation;
import com.jornadamilhas.api.services.OpenAI;
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
    private String photoPath2;
    private String meta;
    private String description;
    private Float price;
    private boolean active;

    public Destination(DestinationPostData destinationPostData)
    {
        this.active = true;
        this.name = destinationPostData.name();
        this.photoPath = destinationPostData.photoPath();
        this.photoPath2 = destinationPostData.photoPath2();
        this.meta = destinationPostData.meta();
        this.description = destinationPostData.description();
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

        if(!StringValidation.isNullEmptyOrBlank(destinationPutData.photoPath2()))
            this.photoPath = destinationPutData.photoPath2();

        if(!StringValidation.isNullEmptyOrBlank(destinationPutData.meta()))
            this.photoPath = destinationPutData.meta();

        if(!StringValidation.isNullEmptyOrBlank(destinationPutData.description()))
            this.photoPath = destinationPutData.description();

        if(destinationPutData.price() != null)
            this.price = destinationPutData.price();
    }

    public void generateDescription()
    {
        description = OpenAI.generateAIText("Faça um resumo sobre " + name + " enfatizando o porque este lugar é incrível. Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.");
    }
}
