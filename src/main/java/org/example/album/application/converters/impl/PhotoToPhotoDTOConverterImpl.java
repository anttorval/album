package org.example.album.application.converters.impl;

import org.example.album.application.converters.PhotoToPhotoDTOConverter;
import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Photo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PhotoToPhotoDTOConverterImpl implements PhotoToPhotoDTOConverter {

    @Override
    public PhotoDTO convert(Photo photo) {

        if(Objects.isNull(photo)){
            throw new ValidationException("photo cannot be null");
        }

        return PhotoDTO.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

    @Override
    public List<PhotoDTO> convert(List<Photo> photos) {

        if(Objects.isNull(photos)){
            throw new ValidationException("photos cannot be null");
        }

        return photos.stream().map(this::convert).toList();
    }
}
