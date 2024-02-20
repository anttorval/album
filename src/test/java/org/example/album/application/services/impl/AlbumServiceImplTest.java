package org.example.album.application.services.impl;

import org.example.album.application.converters.AlbumToAlbumDTOConverter;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
import org.example.album.domain.ports.input.EnrichAlbumsUseCase;
import org.example.album.domain.ports.input.GetAllAlbumsUseCase;
import org.example.album.domain.ports.input.SaveAlbumsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceImplTest {

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;
    @Mock
    private GetAllAlbumsUseCase getAllAlbumsUseCase;

    @Mock
    private EnrichAlbumsUseCase enrichAlbumsUseCase;

    @Mock
    private SaveAlbumsUseCase saveAlbumsUseCase;

    @Mock
    private AlbumToAlbumDTOConverter albumToAlbumDTOConverter;

    private List<Album> albums;
    private List<AlbumDTO> albumsDTO;

    @BeforeEach
    void init() {
        albums = this.getValidAlbums();
        albumsDTO = this.getValidAlbumsDTO();
    }

    @Test
    void givenValidAlbums_whenGetAllAlbumsWithPhotos_thenSucceed() {
        // Given
        when(getAllAlbumsUseCase.getAllAlbumsWithPhotos()).thenReturn(albums);
        when(albumToAlbumDTOConverter.convert(albums)).thenReturn(albumsDTO);

        // When
        List<AlbumDTO> allAlbumsDTO = albumServiceImpl.getAllAlbumsWithPhotos();

        // Then
        verify(getAllAlbumsUseCase, times(1)).getAllAlbumsWithPhotos();
        verify(albumToAlbumDTOConverter, times(1)).convert(albums);

        assertNotNull(allAlbumsDTO);

        assertEquals(albums.size(), allAlbumsDTO.size());
        assertEquals(albums.get(0).getPhotos().size(), allAlbumsDTO.get(0).getPhotos().size());
    }

    @Test
    void givenValidAlbums_whenEnrichAndSaveAlbums_thenSucceed() {
        // Given
        when(enrichAlbumsUseCase.enrichAndReturnAlbumsFromExternalAPI()).thenReturn(albums);

        // When
        albumServiceImpl.enrichAndSaveAlbums();

        // Then
        verify(enrichAlbumsUseCase, times(1)).enrichAndReturnAlbumsFromExternalAPI();
        verify(saveAlbumsUseCase, times(1)).saveAlbums(albums);

    }

    @Test
    void givenValidAlbums_whenEnrichAndReturnAlbums_thenSucceed() {
        // Given
        when(enrichAlbumsUseCase.enrichAndReturnAlbumsFromExternalAPI()).thenReturn(albums);
        when(albumToAlbumDTOConverter.convert(albums)).thenReturn(albumsDTO);

        // When
        List<AlbumDTO> allAlbumsDTO =  albumServiceImpl.enrichAndReturnAlbums();

        // Then
        verify(enrichAlbumsUseCase, times(1)).enrichAndReturnAlbumsFromExternalAPI();
        verify(albumToAlbumDTOConverter, times(1)).convert(albums);

        assertNotNull(allAlbumsDTO);

        assertEquals(albums.size(), allAlbumsDTO.size());
        assertEquals(albums.get(0).getPhotos().size(), allAlbumsDTO.get(0).getPhotos().size());
    }

    private List<Album> getValidAlbums() {
        List<Photo> photosAlbum1 = new ArrayList<>();
        List<Photo> photosAlbum2 = new ArrayList<>();

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
        Photo photo3 = Photo.builder()
                .id(3L)
                .title("Photo Three")
                .albumId(2L)
                .url("www.url3.com")
                .thumbnailUrl("www.url-thumbnail-3.com")
                .build();

        photosAlbum1.add(photo1);
        photosAlbum1.add(photo2);
        photosAlbum2.add(photo3);

        List<Album> albums = new ArrayList<>();

        Album album1 = Album.builder()
                .id(1L)
                .title("Album One")
                .photos(photosAlbum1)
                .build();
        Album album2 = Album.builder()
                .id(2L)
                .title("Album Two")
                .photos(photosAlbum2)
                .build();

        albums.add(album1);
        albums.add(album2);

        return albums;
    }

    private List<AlbumDTO> getValidAlbumsDTO() {
        List<PhotoDTO> photosAlbum1 = new ArrayList<>();
        List<PhotoDTO> photosAlbum2 = new ArrayList<>();

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
        PhotoDTO photo3 = PhotoDTO.builder()
                .id(3L)
                .title("Photo Three")
                .url("www.url3.com")
                .thumbnailUrl("www.url-thumbnail-3.com")
                .build();

        photosAlbum1.add(photo1);
        photosAlbum1.add(photo2);
        photosAlbum2.add(photo3);

        List<AlbumDTO> albums = new ArrayList<>();

        AlbumDTO album1 = AlbumDTO.builder()
                .id(1L)
                .title("Album One")
                .photos(photosAlbum1)
                .build();
        AlbumDTO album2 = AlbumDTO.builder()
                .id(2L)
                .title("Album Two")
                .photos(photosAlbum2)
                .build();

        albums.add(album1);
        albums.add(album2);

        return albums;
    }
}
