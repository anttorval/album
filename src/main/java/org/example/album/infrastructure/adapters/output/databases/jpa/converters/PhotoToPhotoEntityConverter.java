package org.example.album.infrastructure.adapters.output.databases.jpa.converters;

import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;

import java.util.List;

public interface PhotoToPhotoEntityConverter {

    PhotoEntity convert(Photo photo);
    List<PhotoEntity> convert(List<Photo> photoList);
}
