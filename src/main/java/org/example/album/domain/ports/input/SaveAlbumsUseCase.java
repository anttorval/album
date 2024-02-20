package org.example.album.domain.ports.input;

import org.example.album.domain.models.Album;

import java.util.List;

public interface SaveAlbumsUseCase {

    List<Album> saveAlbums(List<Album> albums);
}
