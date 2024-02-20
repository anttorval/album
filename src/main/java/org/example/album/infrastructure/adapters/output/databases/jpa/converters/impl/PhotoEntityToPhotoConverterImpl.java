package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.PhotoEntityToPhotoConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PhotoEntityToPhotoConverterImpl implements PhotoEntityToPhotoConverter {

    @Override
    public Photo convert(PhotoEntity photoEntity) {

        if(Objects.isNull(photoEntity)){
            throw new ValidationException("photoEntity cannot be null");
        }

        return Photo.builder()
                .id(photoEntity.getId())
                .title(photoEntity.getTitle())
                .url(photoEntity.getUrl())
                .thumbnailUrl(photoEntity.getThumbnailUrl())
                .build();
    }

    @Override
    public List<Photo> convert(List<PhotoEntity> photoEntityList) {

        if(Objects.isNull(photoEntityList)){
            throw new ValidationException("photoEntityList cannot be null");
        }

        return photoEntityList.stream().map(this::convert).toList();
    }
}
