package org.example.album.application.adapters;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.application.ports.AlbumProviderPort;
import org.example.album.application.ports.PhotoProviderPort;
import org.example.album.application.ports.dtos.AlbumDTO;
import org.example.album.application.ports.dtos.PhotoDTO;
import org.example.album.domain.models.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrichAlbumsAdapterTest {

    @InjectMocks
    private EnrichAlbumsAdapter enrichAlbumsAdapter;

    @Mock
    private AlbumProviderPort albumProviderPort;

    @Mock
    private PhotoProviderPort photoProviderPort;

    private List<AlbumDTO> albums;
    private List<PhotoDTO> photos;

    @BeforeEach
    void init() {
        albums = this.getValidAlbums();
        photos = this.getValidPhotos();
    }

    @Test
    void givenValidAlbumsAndPhotosFromProvider_whenEnrichAndReturnAlbumsFromExternalAPI_thenSucceed() {
        // Given
        when(albumProviderPort.getAllAlbums()).thenReturn(Mono.just(albums));
        when(photoProviderPort.getAllPhotos()).thenReturn(Mono.just(photos));

        // When
        List<Album> enrichedAlbums = enrichAlbumsAdapter.enrichAndReturnAlbumsFromExternalAPI();

        // Then
        verify(albumProviderPort, times(1)).getAllAlbums();
        verify(photoProviderPort, times(1)).getAllPhotos();

        assertNotNull(enrichedAlbums);

        assertEquals(albums.size(), enrichedAlbums.size());

        assertEquals(2, enrichedAlbums.get(0).getPhotos().size());
        assertEquals(1, enrichedAlbums.get(1).getPhotos().size());

        assertEquals(albums.get(0).getId(), enrichedAlbums.get(0).getPhotos().get(0).getAlbumId());
        assertEquals(albums.get(0).getId(), enrichedAlbums.get(0).getPhotos().get(1).getAlbumId());

        assertEquals(albums.get(1).getId(), enrichedAlbums.get(1).getPhotos().get(0).getAlbumId());
    }

    @Test
    void givenEmptyAlbumsAndPhotosFromProvider_whenEnrichAndReturnAlbumsFromExternalAPI_thenValidationException() {
        // Given
        when(albumProviderPort.getAllAlbums()).thenReturn(Mono.empty());
        when(photoProviderPort.getAllPhotos()).thenReturn(Mono.empty());

        // When
        // Then
        assertThrows(ValidationException.class, () -> enrichAlbumsAdapter.enrichAndReturnAlbumsFromExternalAPI());
    }

    private List<PhotoDTO> getValidPhotos() {
        List<PhotoDTO> photos = new ArrayList<>();

        PhotoDTO photo1 = PhotoDTO.builder()
                .id(1L)
                .title("Photo One")
                .albumId(1L)
                .url("www.url1.com")
                .thumbnailUrl("www.url-thumbnail-1.com")
                .build();
        PhotoDTO photo2 = PhotoDTO.builder()
                .id(2L)
                .title("Photo Two")
                .albumId(1L)
                .url("www.url2.com")
                .thumbnailUrl("www.url-thumbnail-2.com")
                .build();
        PhotoDTO photo3 = PhotoDTO.builder()
                .id(3L)
                .title("Photo Three")
                .albumId(2L)
                .url("www.url3.com")
                .thumbnailUrl("www.url-thumbnail-3.com")
                .build();

        photos.add(photo1);
        photos.add(photo2);
        photos.add(photo3);

        return photos;
    }

    private List<AlbumDTO> getValidAlbums() {
        List<AlbumDTO> albums = new ArrayList<>();

        AlbumDTO album1 = AlbumDTO.builder()
                .id(1L)
                .title("Album One")
                .build();
        AlbumDTO album2 = AlbumDTO.builder()
                .id(2L)
                .title("Album Two")
                .build();

        albums.add(album1);
        albums.add(album2);

        return albums;
    }
}
