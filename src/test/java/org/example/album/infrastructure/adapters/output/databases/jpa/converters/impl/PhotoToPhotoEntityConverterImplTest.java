package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import org.example.album.application.exceptions.ValidationException;
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
class PhotoToPhotoEntityConverterImplTest {

    @InjectMocks
    private PhotoToPhotoEntityConverterImpl photoToPhotoEntityConverterImpl;

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
        PhotoEntity photoEntity = photoToPhotoEntityConverterImpl.convert(photo);

        // Then
        assertNotNull(photoEntity);

        assertEquals(photo.getId(), photoEntity.getId());
        assertEquals(photo.getTitle(), photoEntity.getTitle());
        assertEquals(photo.getUrl(), photoEntity.getUrl());
        assertEquals(photo.getThumbnailUrl(), photoEntity.getThumbnailUrl());
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
        List<PhotoEntity> photoEntities = photoToPhotoEntityConverterImpl.convert(photos);

        // Then
        assertNotNull(photoEntities);

        assertEquals(photos.size(), photoEntities.size());
        assertEquals(photos.get(0).getId(), photoEntities.get(0).getId());
        assertEquals(photos.get(0).getTitle(), photoEntities.get(0).getTitle());
        assertEquals(photos.get(0).getUrl(), photoEntities.get(0).getUrl());
        assertEquals(photos.get(0).getThumbnailUrl(), photoEntities.get(0).getThumbnailUrl());
    }

    @Test
    void givenNullPhoto_whenConvert_thenValidationException() {
        // Given
        Photo photo = null;

        // Then
        assertThrows(ValidationException.class, () -> photoToPhotoEntityConverterImpl.convert(photo));
    }

    @Test
    void givenNullPhotos_whenConvert_thenValidationException() {
        // Given
        List<Photo> photos = null;

        // Then
        assertThrows(ValidationException.class, () -> photoToPhotoEntityConverterImpl.convert(photos));
    }
}
