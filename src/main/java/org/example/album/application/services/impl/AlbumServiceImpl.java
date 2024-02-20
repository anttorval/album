package org.example.album.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.album.application.converters.AlbumToAlbumDTOConverter;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.application.services.AlbumService;
import org.example.album.domain.models.Album;
import org.example.album.domain.ports.input.EnrichAlbumsUseCase;
import org.example.album.domain.ports.input.GetAllAlbumsUseCase;
import org.example.album.domain.ports.input.SaveAlbumsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final GetAllAlbumsUseCase getAllAlbumsUseCase;
    private final EnrichAlbumsUseCase enrichAlbumsUseCase;
    private final SaveAlbumsUseCase saveAlbumsUseCase;
    private final AlbumToAlbumDTOConverter albumToAlbumDTOConverter;

    @Override
    public List<AlbumDTO> getAllAlbumsWithPhotos() {
        List<Album> albumList = this.getAllAlbumsUseCase.getAllAlbumsWithPhotos();
        return this.albumToAlbumDTOConverter.convert(albumList);
    }

    @Override
    public void enrichAndSaveAlbums() {
        List<Album> enrichedAlbumList = this.enrichAlbumsUseCase.enrichAndReturnAlbumsFromExternalAPI();
        this.saveAlbumsUseCase.saveAlbums(enrichedAlbumList);
    }

    @Override
    public List<AlbumDTO> enrichAndReturnAlbums() {
        List<Album> enrichedAlbumList = this.enrichAlbumsUseCase.enrichAndReturnAlbumsFromExternalAPI();
        return this.albumToAlbumDTOConverter.convert(enrichedAlbumList);
    }
}
