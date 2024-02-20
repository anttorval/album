package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import lombok.RequiredArgsConstructor;
import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Album;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumEntityToAlbumConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.PhotoEntityToPhotoConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AlbumEntityToAlbumConverterImpl implements AlbumEntityToAlbumConverter {

    private final PhotoEntityToPhotoConverter photoEntityToPhotoConverter;

    @Override
    public Album convert(AlbumEntity albumEntity) {

        if(Objects.isNull(albumEntity)){
            throw new ValidationException("albumEntity cannot be null");
        }

        return Album.builder()
                .id(albumEntity.getId())
                .title(albumEntity.getTitle())
                .photos(this.photoEntityToPhotoConverter.convert(albumEntity.getPhotos()))
                .build();
    }

    @Override
    public List<Album> convert(List<AlbumEntity> albumEntityList) {

        if(Objects.isNull(albumEntityList)){
            throw new ValidationException("albumEntityList cannot be null");
        }

        return albumEntityList.stream().map(this::convert).toList();
    }
}
