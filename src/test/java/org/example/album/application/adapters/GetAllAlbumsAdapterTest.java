package org.example.album.application.adapters;

import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
import org.example.album.domain.ports.output.GetAlbumsPort;
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
class GetAllAlbumsAdapterTest {

    @InjectMocks
    private GetAllAlbumsAdapter getAllAlbumsAdapter;

    @Mock
    private GetAlbumsPort getAlbumsPort;

    private List<Album> albums;

    @BeforeEach
    void init() {
        albums = this.getValidAlbums();
    }

    @Test
    void givenValidAlbums_whenGetAllAlbumsWithPhotos_thenSucceed() {
        // Given
        when(getAlbumsPort.getAll()).thenReturn(albums);

        // When
        List<Album> allAlbums = getAllAlbumsAdapter.getAllAlbumsWithPhotos();

        // Then
        verify(getAlbumsPort, times(1)).getAll();

        assertNotNull(allAlbums);
        assertEquals(albums.size(), allAlbums.size());
    }

    @Test
    void givenEmptyAlbums_whenGetAllAlbumsWithPhotos_thenSucceed() {
        // Given
        when(getAlbumsPort.getAll()).thenReturn(new ArrayList<>());

        // When
        List<Album> allAlbums = getAllAlbumsAdapter.getAllAlbumsWithPhotos();

        // Then
        verify(getAlbumsPort, times(1)).getAll();

        assertNotNull(allAlbums);
        assertEquals(0, allAlbums.size());
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
}
