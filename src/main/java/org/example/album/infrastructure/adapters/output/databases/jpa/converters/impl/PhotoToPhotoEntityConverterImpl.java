package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.PhotoToPhotoEntityConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
public class PhotoToPhotoEntityConverterImpl implements PhotoToPhotoEntityConverter {

    @Override
    public PhotoEntity convert(Photo photo) {

        if(Objects.isNull(photo)){
            throw new ValidationException("photo cannot be null");
        }

        return PhotoEntity.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .album(AlbumEntity.builder().id(photo.getAlbumId()).build())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

    @Override
    public List<PhotoEntity> convert(List<Photo> photoList) {

        if(Objects.isNull(photoList)){
            throw new ValidationException("photoList cannot be null");
        }

        return photoList.stream().map(this::convert).toList();
    }
}
