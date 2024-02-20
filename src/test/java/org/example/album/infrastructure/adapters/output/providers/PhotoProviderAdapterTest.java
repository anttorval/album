package org.example.album.infrastructure.adapters.output.providers;

import org.example.album.application.ports.dtos.PhotoDTO;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhotoProviderAdapterTest {

    @InjectMocks
    private PhotoProviderAdapter photoProviderAdapter;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Test
    void whenGetAllPhotos_thenSucceed() {
        //Given
        List<PhotoDTO> photos = getValidPhotosDTO();

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/photos")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(PhotoDTO.class)).thenReturn(Flux.fromIterable(photos));

        //When
        Mono<List<PhotoDTO>> result = photoProviderAdapter.getAllPhotos();
        List<PhotoDTO> photosResult = result.block();

        //Then
        assertNotNull(result);
        assertEquals(photos.size(), photosResult.size());
        assertEquals(photos.get(0).getId(), photosResult.get(0).getId());
        assertEquals(photos.get(0).getTitle(), photosResult.get(0).getTitle());
    }

    @Test
    void givenInoperativeProvider_whenGetAllPhotos_thenProviderException() {
        //Given
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/photos")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenThrow(new RuntimeException("Connection timed out"));

        //When
        //Then
        assertThrows(ProviderException.class, () -> photoProviderAdapter.getAllPhotos());
    }

    private List<PhotoDTO> getValidPhotosDTO() {
        List<PhotoDTO> Photos = new ArrayList<>();

        PhotoDTO album1 = PhotoDTO.builder()
                .id(1L)
                .albumId(1L)
                .title("Photo One")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        Photos.add(album1);

        return Photos;
    }
}
