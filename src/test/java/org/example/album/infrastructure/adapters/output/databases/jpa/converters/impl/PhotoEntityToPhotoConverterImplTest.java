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

@ExtendWith(MockitoExtension.class)
class PhotoEntityToPhotoConverterImplTest {

    @InjectMocks
    private PhotoEntityToPhotoConverterImpl photoEntityToPhotoConverterImpl;

    @Test
    void givenValidPhotoEntity_whenConvert_thenSucceed() {
        //Given
        PhotoEntity photoEntity = PhotoEntity.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        // When
        Photo photo = photoEntityToPhotoConverterImpl.convert(photoEntity);

        // Then
        assertNotNull(photo);

        assertEquals(photoEntity.getId(), photo.getId());
        assertEquals(photoEntity.getTitle(), photo.getTitle());
        assertEquals(photoEntity.getUrl(), photo.getUrl());
        assertEquals(photoEntity.getThumbnailUrl(), photo.getThumbnailUrl());
    }

    @Test
    void givenValidPhotoEntities_whenConvert_thenSucceed() {
        //Given
        List<PhotoEntity> photoEntities = new ArrayList<>();
        PhotoEntity photoEntity = PhotoEntity.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();
        photoEntities.add(photoEntity);

        // When
        List<Photo> photos = photoEntityToPhotoConverterImpl.convert(photoEntities);

        // Then
        assertNotNull(photos);

        assertEquals(photoEntities.size(), photos.size());
        assertEquals(photoEntities.get(0).getId(), photos.get(0).getId());
        assertEquals(photoEntities.get(0).getTitle(), photos.get(0).getTitle());
        assertEquals(photoEntities.get(0).getUrl(), photos.get(0).getUrl());
        assertEquals(photoEntities.get(0).getThumbnailUrl(), photos.get(0).getThumbnailUrl());
    }

    @Test
    void givenNullPhotoEntity_whenConvert_thenValidationException() {
        // Given
        PhotoEntity photoEntity = null;

        // Then
        assertThrows(ValidationException.class, () -> photoEntityToPhotoConverterImpl.convert(photoEntity));
    }

    @Test
    void givenNullPhotoEntities_whenConvert_thenValidationException() {
        // Given
        List<PhotoEntity> photoEntities = null;

        // Then
        assertThrows(ValidationException.class, () -> photoEntityToPhotoConverterImpl.convert(photoEntities));
    }
}
