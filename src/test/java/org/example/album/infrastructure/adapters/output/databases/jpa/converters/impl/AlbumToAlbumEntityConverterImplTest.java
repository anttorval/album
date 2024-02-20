package org.example.album.infrastructure.adapters.output.databases.jpa.converters.impl;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.PhotoToPhotoEntityConverter;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumToAlbumEntityConverterImplTest {

    @InjectMocks
    private AlbumToAlbumEntityConverterImpl albumToAlbumEntityConverterImpl;

    @Mock
    private PhotoToPhotoEntityConverter photoToPhotoEntityConverter;

    private List<PhotoEntity> photoEntities;
    private Album album;

    @BeforeEach
    void init() {
        photoEntities = this.getValidPhotoEntities();
        album = this.getValidAlbum();
    }

    @Test
    void givenValidAlbum_whenConvert_thenSucceed() {
        //Given
        when(photoToPhotoEntityConverter.convert(anyList())).thenReturn(photoEntities);

        // When
        AlbumEntity albumEntity = albumToAlbumEntityConverterImpl.convert(album);

        // Then
        assertNotNull(albumEntity);

        assertEquals(album.getId(), albumEntity.getId());
        assertEquals(album.getTitle(), albumEntity.getTitle());
        assertEquals(album.getPhotos().size(), albumEntity.getPhotos().size());
    }

    @Test
    void givenValidAlbums_whenConvert_thenSucceed() {
        //Given
        List<Album> albums = new ArrayList<>();
        albums.add(album);
        when(photoToPhotoEntityConverter.convert(anyList())).thenReturn(photoEntities);

        // When
        List<AlbumEntity> albumEntities = albumToAlbumEntityConverterImpl.convert(albums);

        // Then
        assertNotNull(albumEntities);

        assertEquals(albums.size(), albumEntities.size());
        assertEquals(albums.get(0).getId(), albumEntities.get(0).getId());
        assertEquals(albums.get(0).getTitle(), albumEntities.get(0).getTitle());
        assertEquals(albums.get(0).getPhotos().size(), albumEntities.get(0).getPhotos().size());
    }

    @Test
    void givenNullAlbum_whenConvert_thenValidationException() {
        // Given
        Album album = null;

        // Then
        assertThrows(ValidationException.class, () -> albumToAlbumEntityConverterImpl.convert(album));
    }

    @Test
    void givenNullAlbums_whenConvert_thenValidationException() {
        // Given
        List<Album> albums = null;

        // Then
        assertThrows(ValidationException.class, () -> albumToAlbumEntityConverterImpl.convert(albums));
    }

    private List<PhotoEntity> getValidPhotoEntities() {
        PhotoEntity photoEntity = PhotoEntity.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        List<PhotoEntity> photoEntities = new ArrayList<>();
        photoEntities.add(photoEntity);

        return photoEntities;
    }

    private Album getValidAlbum() {
        List<Photo> photosAlbum = new ArrayList<>();

        Photo photo1 = Photo.builder()
                .id(1L)
                .albumId(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        photosAlbum.add(photo1);

        Album album = Album.builder()
                .id(1L)
                .title("Album One")
                .photos(photosAlbum)
                .build();

        return album;
    }
}
