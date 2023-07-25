package com.jornadamilhas.api.model.destination;

public record DestinationGetData(Long id, String name, String photoPath, Float price)
{
    public DestinationGetData(Destination destination)
    {
        this(destination.getId(), destination.getName(), destination.getPhotoPath(), destination.getPrice());
    }
}
