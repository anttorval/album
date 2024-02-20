package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import lombok.RequiredArgsConstructor;
import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Album;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumToAlbumEntityConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.PhotoToPhotoEntityConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AlbumToAlbumEntityConverterImpl implements AlbumToAlbumEntityConverter {

    private final PhotoToPhotoEntityConverter photoToPhotoEntityConverter;

    @Override
    public AlbumEntity convert(Album album) {

        if(Objects.isNull(album)){
            throw new ValidationException("album cannot be null");
        }

        return AlbumEntity.builder()
                .id(album.getId())
                .title(album.getTitle())
                .photos(this.photoToPhotoEntityConverter.convert(album.getPhotos()))
                .build();
    }

    @Override
    public List<AlbumEntity> convert(List<Album> albumList) {

        if(Objects.isNull(albumList)){
            throw new ValidationException("albumList cannot be null");
        }

        return albumList.stream().map(this::convert).toList();
    }
}
