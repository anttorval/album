package org.example.album.application.ports;

import org.example.album.application.ports.dtos.PhotoDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PhotoProviderPort {

    Mono<List<PhotoDTO>> getAllPhotos();
}
