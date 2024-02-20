package org.example.album.application.converters;

import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.domain.models.Album;

import java.util.List;

public interface AlbumToAlbumDTOConverter {

    AlbumDTO convert(Album album);
    List<AlbumDTO> convert(List<Album> albums);
}
