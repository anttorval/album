package org.example.album.application.services;

import org.example.album.application.services.dtos.AlbumDTO;

import java.util.List;

public interface AlbumService {

    List<AlbumDTO> getAllAlbumsWithPhotos();

    void enrichAndSaveAlbums();

    List<AlbumDTO> enrichAndReturnAlbums();
}
