package org.example.album.infrastructure.adapters.output.databases.jpa.converters;

import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;

import java.util.List;

public interface PhotoEntityToPhotoConverter {

    Photo convert(PhotoEntity photoEntity);
    List<Photo> convert(List<PhotoEntity> photoEntityList);
}
