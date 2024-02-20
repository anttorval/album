package org.example.album.infrastructure.adapters.output.databases.jpa.repositories;

import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumEntityToAlbumConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumToAlbumEntityConverter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersistAlbumsAdapterTest {

    @InjectMocks
    private PersistAlbumsAdapter persistAlbumsAdapter;
    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private AlbumEntityToAlbumConverter albumEntityToAlbumConverter;
    @Mock
    private AlbumToAlbumEntityConverter albumToAlbumEntityConverter;
    private List<AlbumEntity> albumEntityList;
    private List<Album> albums;

    @BeforeEach
    void init() {
        albumEntityList = this.getValidAlbumEntities();
        albums = this.getValidAlbums();
    }

    @Test
    void givenValidAlbums_whenSaveAll_thenSucceed() {
        // Given
        when(albumToAlbumEntityConverter.convert(albums)).thenReturn(albumEntityList);
        when(albumRepository.saveAll(anyList())).thenReturn(albumEntityList);
        when(albumEntityToAlbumConverter.convert(anyList())).thenReturn(albums);

        // When
        List<Album> persistedAlbums = persistAlbumsAdapter.saveAll(albums);

        // Then
        verify(albumToAlbumEntityConverter, times(1)).convert(albums);
        verify(albumRepository, times(1)).saveAll(albumEntityList);
        verify(albumEntityToAlbumConverter, times(1)).convert(albumEntityList);

        assertNotNull(persistedAlbums);

        assertEquals(albums.size(), persistedAlbums.size());
        assertEquals(albums.get(0).getPhotos().size(), persistedAlbums.get(0).getPhotos().size());
    }


    private List<AlbumEntity> getValidAlbumEntities() {
        List<AlbumEntity> albums = new ArrayList<>();
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
        albums.add(albumEntity);

        return albums;
    }

    private List<Album> getValidAlbums() {
        List<Album> albums = new ArrayList<>();
        Photo photo = Photo.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        List<Photo> photos = new ArrayList<>();
        photos.add(photo);

        Album album = Album.builder()
                .id(1L)
                .title("Title album1")
                .photos(photos)
                .build();
        albums.add(album);

        return albums;
    }
}
