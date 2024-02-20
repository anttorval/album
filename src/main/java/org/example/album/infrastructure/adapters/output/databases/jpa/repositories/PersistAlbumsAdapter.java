package org.example.album.infrastructure.adapters.output.databases.jpa.repositories;

import lombok.RequiredArgsConstructor;
import org.example.album.domain.models.Album;
import org.example.album.domain.ports.output.SaveAlbumsPort;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumEntityToAlbumConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.converters.AlbumToAlbumEntityConverter;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersistAlbumsAdapter implements SaveAlbumsPort {

    private final AlbumRepository albumRepository;
    private final AlbumEntityToAlbumConverter albumEntityToAlbumConverter;
    private final AlbumToAlbumEntityConverter albumToAlbumEntityConverter;

    @Override
    public List<Album> saveAll(List<Album> albums) {
        List<AlbumEntity> albumEntityList = this.albumToAlbumEntityConverter.convert(albums);
        List<AlbumEntity> savedAlbumEntityList = this.albumRepository.saveAll(albumEntityList);
        return this.albumEntityToAlbumConverter.convert(savedAlbumEntityList);
    }
}
