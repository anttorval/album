package org.example.album.infrastructure.adapters.input.rest.converters;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.infrastructure.adapters.input.rest.converters.impl.AlbumDTOToGetAlbumsResponseImpl;
import org.example.album.infrastructure.adapters.input.rest.responses.GetAlbumsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlbumDTOToGetAlbumsResponseImplTest {

    @InjectMocks
    private AlbumDTOToGetAlbumsResponseImpl albumDTOToGetAlbumsResponseImpl;

    private List<AlbumDTO> albumsDTO;

    @BeforeEach
    void init() {
        albumsDTO = this.getValidAlbumsDTO();
    }


    @Test
    void givenValidAlbumsDTO_whenConvert_thenSucceed() {
        // When
        GetAlbumsResponse getAlbumsResponse = albumDTOToGetAlbumsResponseImpl.convert(albumsDTO);

        // Then
        assertNotNull(getAlbumsResponse);

        assertEquals(albumsDTO.size(), getAlbumsResponse.getAlbums().size());
        assertEquals(albumsDTO.get(0).getId(), getAlbumsResponse.getAlbums().get(0).getId());
        assertEquals(albumsDTO.get(0).getTitle(), getAlbumsResponse.getAlbums().get(0).getTitle());

        assertEquals(albumsDTO.get(0).getPhotos().size(), getAlbumsResponse.getAlbums().get(0).getPhotos().size());
        assertEquals(albumsDTO.get(0).getPhotos().get(0).getId(), getAlbumsResponse.getAlbums().get(0).getPhotos().get(0).getId());
        assertEquals(albumsDTO.get(0).getPhotos().get(0).getTitle(), getAlbumsResponse.getAlbums().get(0).getPhotos().get(0).getTitle());
        assertEquals(albumsDTO.get(0).getPhotos().get(0).getUrl(), getAlbumsResponse.getAlbums().get(0).getPhotos().get(0).getUrl());
        assertEquals(albumsDTO.get(0).getPhotos().get(0).getThumbnailUrl(), getAlbumsResponse.getAlbums().get(0).getPhotos().get(0).getThumbnailUrl());
    }


    @Test
    void givenNullAlbumsDTO_whenConvert_thenValidationException() {
        // Given
        List<AlbumDTO> albumsDTO = null;

        // Then
        assertThrows(ValidationException.class, () -> albumDTOToGetAlbumsResponseImpl.convert(albumsDTO));
    }

    private List<AlbumDTO> getValidAlbumsDTO() {
        List<PhotoDTO> photosAlbum1 = new ArrayList<>();

        PhotoDTO photo1 = PhotoDTO.builder()
                .id(1L)
                .title("Photo One")
                .url("www.url1.com")
                .thumbnailUrl("www.url-thumbnail-1.com")
                .build();

        photosAlbum1.add(photo1);

        List<AlbumDTO> albums = new ArrayList<>();

        AlbumDTO album1 = AlbumDTO.builder()
                .id(1L)
                .title("Album One")
                .photos(photosAlbum1)
                .build();

        albums.add(album1);

        return albums;
    }
}
