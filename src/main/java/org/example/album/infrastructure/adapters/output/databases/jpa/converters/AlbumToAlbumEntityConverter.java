package org.example.album.infrastructure.adapters.output.databases.jpa.converters;

import org.example.album.domain.models.Album;
import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;

import java.util.List;

public interface AlbumToAlbumEntityConverter {

    AlbumEntity convert(Album album);
    List<AlbumEntity> convert(List<Album> albumList);
}
