package org.example.album.application.ports;

import org.example.album.application.ports.dtos.AlbumDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AlbumProviderPort {

    Mono<List<AlbumDTO>> getAllAlbums();
}
