package org.example.album.infrastructure.adapters.output.providers;

import org.example.album.application.ports.dtos.AlbumDTO;
import org.example.album.application.ports.AlbumProviderPort;
import org.example.album.infrastructure.adapters.exceptions.ProviderException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AlbumProviderAdapter extends AbstractProvider implements AlbumProviderPort{

    protected AlbumProviderAdapter(WebClient client) {
        super(client);
    }

    @Override
    public Mono<List<AlbumDTO>> getAllAlbums() {
        try{
            return super.getClient().get().uri("/albums").retrieve().bodyToFlux(AlbumDTO.class).collectList();
        }catch (Exception e){
            throw new ProviderException("Error calling albums provider");
        }
    }
}
