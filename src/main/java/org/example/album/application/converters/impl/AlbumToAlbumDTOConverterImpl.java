package org.example.album.application.converters.impl;

import lombok.RequiredArgsConstructor;
import org.example.album.application.converters.AlbumToAlbumDTOConverter;
import org.example.album.application.converters.PhotoToPhotoDTOConverter;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Album;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AlbumToAlbumDTOConverterImpl implements AlbumToAlbumDTOConverter {

    private final PhotoToPhotoDTOConverter photoToPhotoDTOConverter;

    @Override
    public AlbumDTO convert(Album album) {

        if(Objects.isNull(album)){
            throw new ValidationException("album cannot be null");
        }

        return AlbumDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .photos(this.photoToPhotoDTOConverter.convert(album.getPhotos()))
                .build();
    }

    @Override
    public List<AlbumDTO> convert(List<Album> albums) {

        if(Objects.isNull(albums)){
            throw new ValidationException("albums cannot be null");
        }

        return albums.stream().map(this::convert).toList();
    }
}
