package org.example.album.infrastructure.adapters.output.providers;

import org.example.album.application.ports.dtos.AlbumDTO;
import org.example.album.infrastructure.adapters.exceptions.ProviderException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumProviderAdapterTest {

    @InjectMocks
    private AlbumProviderAdapter albumProviderAdapter;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Test
    void whenGetAllAlbums_thenSucceed() {
        //Given
        List<AlbumDTO> albums = getValidAlbumsDTO();

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/albums")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(AlbumDTO.class)).thenReturn(Flux.fromIterable(albums));

        //When
        Mono<List<AlbumDTO>> result = albumProviderAdapter.getAllAlbums();
        List<AlbumDTO> albumsResult = result.block();

        //Then
        assertNotNull(result);
        assertEquals(albums.size(), albumsResult.size());
        assertEquals(albums.get(0).getId(), albumsResult.get(0).getId());
        assertEquals(albums.get(0).getTitle(), albumsResult.get(0).getTitle());
    }

    @Test
    void givenInoperativeProvider_whenGetAllAlbums_thenProviderException() {
        //Given
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/albums")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenThrow(new RuntimeException("Connection timed out"));

        //When
        //Then
        assertThrows(ProviderException.class, () -> albumProviderAdapter.getAllAlbums());
    }

    private List<AlbumDTO> getValidAlbumsDTO() {
        List<AlbumDTO> albums = new ArrayList<>();

        AlbumDTO album1 = AlbumDTO.builder()
                .id(1L)
                .title("Album One")
                .build();

        albums.add(album1);

        return albums;
    }
}
