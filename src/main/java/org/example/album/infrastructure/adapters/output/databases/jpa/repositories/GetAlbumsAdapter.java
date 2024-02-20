package org.example.album.infrastructure.adapters.output.databases.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.example.album.domain.models.Album;
import org.example.album.domain.ports.output.GetAlbumsPort;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumEntityToAlbumConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAlbumsAdapter implements GetAlbumsPort {

    private final AlbumRepository albumRepository;
    private final AlbumEntityToAlbumConverter albumEntityToAlbumConverter;

    @Override
    public List<Album> getAll() {
        List<AlbumEntity> albumEntityList = this.albumRepository.findAll();
        return this.albumEntityToAlbumConverter.convert(albumEntityList);
    }
}
