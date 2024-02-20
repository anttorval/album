package org.example.album.infrastructure.adapters.output.providers;

import org.example.album.application.ports.dtos.PhotoDTO;
import org.example.album.application.ports.PhotoProviderPort;
import org.example.album.infrastructure.adapters.exceptions.ProviderException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PhotoProviderAdapter extends AbstractProvider implements PhotoProviderPort {

    protected PhotoProviderAdapter(WebClient client) {
        super(client);
    }

    @Override
    public Mono<List<PhotoDTO>> getAllPhotos() {
        try{
            return super.getClient().get().uri("/photos").retrieve().bodyToFlux(PhotoDTO.class).collectList();
        }catch (Exception e){
            throw new ProviderException("Error calling photos provider");
        }
    }
}
