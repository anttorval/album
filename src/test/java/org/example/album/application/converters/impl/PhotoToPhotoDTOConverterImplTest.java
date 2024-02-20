package org.example.album.application.converters.impl;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PhotoToPhotoDTOConverterImplTest {

    @InjectMocks
    private PhotoToPhotoDTOConverterImpl photoToPhotoDTOConverterImpl;

    @Test
    void givenValidPhoto_whenConvert_thenSucceed() {
        //Given
        Photo photo = Photo.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        // When
        PhotoDTO photoDTO = photoToPhotoDTOConverterImpl.convert(photo);

        // Then
        assertNotNull(photoDTO);

        assertEquals(photo.getId(), photoDTO.getId());
        assertEquals(photo.getTitle(), photoDTO.getTitle());
        assertEquals(photo.getUrl(), photoDTO.getUrl());
        assertEquals(photo.getThumbnailUrl(), photoDTO.getThumbnailUrl());
    }

    @Test
    void givenValidPhotos_whenConvert_thenSucceed() {
        //Given
        List<Photo> photos = new ArrayList<>();
        Photo photo = Photo.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();
        photos.add(photo);

        // When
        List<PhotoDTO> photoDTOs = photoToPhotoDTOConverterImpl.convert(photos);

        // Then
        assertNotNull(photoDTOs);

        assertEquals(photos.size(), photoDTOs.size());
        assertEquals(photos.get(0).getId(), photoDTOs.get(0).getId());
        assertEquals(photos.get(0).getTitle(), photoDTOs.get(0).getTitle());
        assertEquals(photos.get(0).getUrl(), photoDTOs.get(0).getUrl());
        assertEquals(photos.get(0).getThumbnailUrl(), photoDTOs.get(0).getThumbnailUrl());
    }

    @Test
    void givenNullPhoto_whenConvert_thenValidationException() {
        // Given
        Photo photo = null;

        // Then
        assertThrows(ValidationException.class, () -> photoToPhotoDTOConverterImpl.convert(photo));
    }

    @Test
    void givenNullPhotos_whenConvert_thenValidationException() {
        // Given
        List<Photo> photos = null;

        // Then
        assertThrows(ValidationException.class, () -> photoToPhotoDTOConverterImpl.convert(photos));
    }
}
