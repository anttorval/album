package org.example.album.application.converters.impl;

import org.example.album.application.converters.PhotoToPhotoDTOConverter;
import org.example.album.application.exceptions.ValidationException;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
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
class AlbumToAlbumDTOConverterImplTest {

    @InjectMocks
    private AlbumToAlbumDTOConverterImpl albumToAlbumDTOConverterImpl;

    @Mock
    private PhotoToPhotoDTOConverter photoToPhotoDTOConverter;

    private Album validAlbum;
    private List<PhotoDTO> validPhotosDTO;

    @BeforeEach
    void init() {
        validAlbum = this.getValidAlbum();
        validPhotosDTO = this.getValidPhotosDTO();
    }

    @Test
    void givenValidAlbum_whenConvert_thenSucceed() {
        // Given
        when(photoToPhotoDTOConverter.convert(validAlbum.getPhotos())).thenReturn(validPhotosDTO);

        // When
        AlbumDTO albumDTO = albumToAlbumDTOConverterImpl.convert(validAlbum);

        // Then
        assertNotNull(albumDTO);

        assertEquals(validAlbum.getId(), albumDTO.getId());
        assertEquals(validAlbum.getTitle(), albumDTO.getTitle());
        assertEquals(validAlbum.getPhotos().size(), albumDTO.getPhotos().size());
    }

    @Test
    void givenValidAlbumList_whenConvert_thenSucceed() {
        // Given
        List<Album> validAlbumList = new ArrayList<>();
        validAlbumList.add(validAlbum);

        when(photoToPhotoDTOConverter.convert(validAlbum.getPhotos())).thenReturn(validPhotosDTO);

        // When
        List<AlbumDTO> albumDTOList = albumToAlbumDTOConverterImpl.convert(validAlbumList);

        // Then
        assertNotNull(albumDTOList);
        assertEquals(1, albumDTOList.size());

        assertEquals(validAlbumList.get(0).getId(), albumDTOList.get(0).getId());
        assertEquals(validAlbumList.get(0).getTitle(), albumDTOList.get(0).getTitle());
        assertEquals(validAlbumList.get(0).getPhotos().size(), albumDTOList.get(0).getPhotos().size());
    }

    @Test
    void givenNullAlbum_whenConvert_thenValidationException() {
        // Given
        Album album = null;

        // Then
        assertThrows(ValidationException.class, () -> albumToAlbumDTOConverterImpl.convert(album));
    }

    @Test
    void givenNullAlbumList_whenConvert_thenValidationException() {
        // Given
        List<Album> albumList = null;

        // Then
        assertThrows(ValidationException.class, () -> albumToAlbumDTOConverterImpl.convert(albumList));
    }

    private Album getValidAlbum() {
        List<Photo> photosAlbum = new ArrayList<>();

        Photo photo1 = Photo.builder()
                .id(1L)
                .title("Photo One")
                .albumId(1L)
                .url("www.url1.com")
                .thumbnailUrl("www.url-thumbnail-1.com")
                .build();
        Photo photo2 = Photo.builder()
                .id(2L)
                .title("Photo Two")
                .albumId(1L)
                .url("www.url2.com")
                .thumbnailUrl("www.url-thumbnail-2.com")
                .build();

        photosAlbum.add(photo1);
        photosAlbum.add(photo2);

        Album album = Album.builder()
                .id(1L)
                .title("Album One")
                .photos(photosAlbum)
                .build();

        return album;
    }

    private List<PhotoDTO> getValidPhotosDTO() {
        List<PhotoDTO> photosAlbum = new ArrayList<>();

        PhotoDTO photo1 = PhotoDTO.builder()
                .id(1L)
                .title("Photo One")
                .url("www.url1.com")
                .thumbnailUrl("www.url-thumbnail-1.com")
                .build();
        PhotoDTO photo2 = PhotoDTO.builder()
                .id(2L)
                .title("Photo Two")
                .url("www.url2.com")
                .thumbnailUrl("www.url-thumbnail-2.com")
                .build();

        photosAlbum.add(photo1);
        photosAlbum.add(photo2);

        return photosAlbum;
    }
}
