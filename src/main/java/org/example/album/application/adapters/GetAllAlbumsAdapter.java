package org.example.album.application.adapters;

import lombok.RequiredArgsConstructor;
import org.example.album.domain.models.Album;
import org.example.album.domain.ports.input.GetAllAlbumsUseCase;
import org.example.album.domain.ports.output.GetAlbumsPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllAlbumsAdapter implements GetAllAlbumsUseCase {

    private final GetAlbumsPort getAlbumsPort;

    @Override
    public List<Album> getAllAlbumsWithPhotos() {
        return getAlbumsPort.getAll();
    }

}
