package org.example.album.infrastructure.adapters.output.databases.jpa.converters;

import org.example.album.domain.models.Album;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;

import java.util.List;

public interface AlbumEntityToAlbumConverter {

    Album convert(AlbumEntity albumEntity);
    List<Album> convert(List<AlbumEntity> albumEntityList);
}
