package com.jornadamilhas.api.model.destination;

public record DestinationGetData(Long id, String name, String photoPath, String photoPath2, String meta, String description, Float price)
{
    public DestinationGetData(Destination destination)
    {
        this(destination.getId(), destination.getName(), destination.getPhotoPath(), destination.getPhotoPath2(), destination.getMeta(), destination.getDescription(), destination.getPrice());
    }
}
