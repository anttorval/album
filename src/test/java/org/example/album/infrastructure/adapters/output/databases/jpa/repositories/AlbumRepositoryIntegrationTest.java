package org.example.album.infrastructure.adapters.output.databases.jpa.repositories;

import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.PhotoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class AlbumRepositoryIntegrationTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void givenValidAlbumsSavedInDB_whenFindAll_thenSucceed() {
        // When
        List<AlbumEntity> albumEntityList = albumRepository.findAll();

        // Then
        assertNotNull(albumEntityList);

        assertEquals(1, albumEntityList.size());
        assertEquals(1, albumEntityList.get(0).getId());
        assertEquals("album 1", albumEntityList.get(0).getTitle());

        assertEquals(2, albumEntityList.get(0).getPhotos().size());

        assertEquals(1, albumEntityList.get(0).getPhotos().get(0).getId());
        assertEquals("photo 1", albumEntityList.get(0).getPhotos().get(0).getTitle());
        assertEquals("www.url_1.com", albumEntityList.get(0).getPhotos().get(0).getUrl());
        assertEquals("www.thumbnailUrl_1.com", albumEntityList.get(0).getPhotos().get(0).getThumbnailUrl());

        assertEquals(2, albumEntityList.get(0).getPhotos().get(1).getId());
        assertEquals("photo 2", albumEntityList.get(0).getPhotos().get(1).getTitle());
        assertEquals("www.url_2.com", albumEntityList.get(0).getPhotos().get(1).getUrl());
        assertEquals("www.thumbnailUrl_2.com", albumEntityList.get(0).getPhotos().get(1).getThumbnailUrl());
    }

    @Test
    void givenValidAlbums_whenSaveAll_thenSucceed() {
        List<PhotoEntity> photos = new ArrayList<>();
        PhotoEntity photo1 = PhotoEntity.builder()
                .id(1L)
                .title("photo 1")
                .url("www.url_1.com")
                .thumbnailUrl("www.thumbnailUrl_1.com")
                .build();

        PhotoEntity photo2 = PhotoEntity.builder()
                .id(2L)
                .title("photo 2")
                .url("www.url_2.com")
                .thumbnailUrl("www.thumbnailUrl_2.com")
                .build();
        photos.add(photo1);
        photos.add(photo2);

        AlbumEntity album1 = AlbumEntity.builder()
                .id(1L)
                .title("album 1")
                .photos(photos)
                .build();
        List<AlbumEntity> albums = new ArrayList<>();
        albums.add(album1);

        // When
        List<AlbumEntity> albumEntityList = albumRepository.saveAll(albums);

        // Then
        assertNotNull(albumEntityList);

        assertEquals(1, albumEntityList.size());
        assertEquals(1, albumEntityList.get(0).getId());
        assertEquals("album 1", albumEntityList.get(0).getTitle());

        assertEquals(2, albumEntityList.get(0).getPhotos().size());

        assertEquals(1, albumEntityList.get(0).getPhotos().get(0).getId());
        assertEquals("photo 1", albumEntityList.get(0).getPhotos().get(0).getTitle());
        assertEquals("www.url_1.com", albumEntityList.get(0).getPhotos().get(0).getUrl());
        assertEquals("www.thumbnailUrl_1.com", albumEntityList.get(0).getPhotos().get(0).getThumbnailUrl());

        assertEquals(2, albumEntityList.get(0).getPhotos().get(1).getId());
        assertEquals("photo 2", albumEntityList.get(0).getPhotos().get(1).getTitle());
        assertEquals("www.url_2.com", albumEntityList.get(0).getPhotos().get(1).getUrl());
        assertEquals("www.thumbnailUrl_2.com", albumEntityList.get(0).getPhotos().get(1).getThumbnailUrl());
    }
}
