package org.example.album.application.converters;

import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.domain.models.Photo;

import java.util.List;

public interface PhotoToPhotoDTOConverter {

    PhotoDTO convert(Photo photo);
    List<PhotoDTO> convert(List<Photo> photos);
}
