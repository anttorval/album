package org.example.album.domain.ports.output;

import org.example.album.domain.models.Album;

import java.util.List;

public interface SaveAlbumsPort {

    List<Album> saveAll(List<Album> albums);
}
