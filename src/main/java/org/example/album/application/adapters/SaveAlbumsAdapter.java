package org.example.album.application.adapters;

import lombok.RequiredArgsConstructor;
import org.example.album.domain.models.Album;
import org.example.album.domain.ports.input.SaveAlbumsUseCase;
import org.example.album.domain.ports.output.SaveAlbumsPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveAlbumsAdapter implements SaveAlbumsUseCase {

    private final SaveAlbumsPort saveAlbumsPort;

    @Override
    public List<Album> saveAlbums(List<Album> albums) {
        return saveAlbumsPort.saveAll(albums);
    }
}
