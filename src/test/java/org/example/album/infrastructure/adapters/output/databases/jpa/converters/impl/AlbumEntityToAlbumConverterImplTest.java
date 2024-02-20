package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.PhotoEntityToPhotoConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumEntityToAlbumConverterImplTest {

    @InjectMocks
    private AlbumEntityToAlbumConverterImpl albumEntityToAlbumConverterImpl;

    @Mock
    private PhotoEntityToPhotoConverter photoEntityToPhotoConverter;

    private AlbumEntity albumEntity;
    private List<Photo> photos;

    @BeforeEach
    void init() {
        albumEntity = this.getValidAlbumEntity();
        photos = this.getValidPhotos();
    }


    @Test
    void givenValidAlbumEntity_whenConvert_thenSucceed() {
        //Given
        when(photoEntityToPhotoConverter.convert(albumEntity.getPhotos())).thenReturn(photos);

        // When
        Album album = albumEntityToAlbumConverterImpl.convert(albumEntity);

        // Then
        assertNotNull(album);

        assertEquals(albumEntity.getId(), album.getId());
        assertEquals(albumEntity.getTitle(), album.getTitle());
        assertEquals(albumEntity.getPhotos().size(), album.getPhotos().size());
    }

    @Test
    void givenValidAlbumEntities_whenConvert_thenSucceed() {
        //Given
        List<AlbumEntity> albumEntities = new ArrayList<>();
        albumEntities.add(albumEntity);

        when(photoEntityToPhotoConverter.convert(albumEntity.getPhotos())).thenReturn(photos);

        // When
        List<Album> albums = albumEntityToAlbumConverterImpl.convert(albumEntities);

        // Then
        assertNotNull(albums);

        assertEquals(albumEntities.size(), albums.size());
        assertEquals(albumEntities.get(0).getPhotos().size(), albums.get(0).getPhotos().size());
    }

    @Test
    void givenNullAlbumEntity_whenConvert_thenValidationException() {
        // Given
        AlbumEntity albumEntity = null;

        // Then
        assertThrows(ValidationException.class, () -> albumEntityToAlbumConverterImpl.convert(albumEntity));
    }

    @Test
    void givenNullAlbumEntities_whenConvert_thenValidationException() {
        // Given
        List<AlbumEntity> albumEntities = null;

        // Then
        assertThrows(ValidationException.class, () -> albumEntityToAlbumConverterImpl.convert(albumEntities));
    }

    private AlbumEntity getValidAlbumEntity() {
        PhotoEntity photoEntity = PhotoEntity.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        List<PhotoEntity> photoEntities = new ArrayList<>();
        photoEntities.add(photoEntity);

        AlbumEntity albumEntity = AlbumEntity.builder()
                .id(1L)
                .title("Title album1")
                .photos(photoEntities)
                .build();

        return albumEntity;
    }

    private List<Photo> getValidPhotos() {
        Photo photo = Photo.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        List<Photo> photoEntities = new ArrayList<>();
        photoEntities.add(photo);

        return photoEntities;
    }
}
