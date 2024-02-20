package org.example.album.domain.ports.input;

import org.example.album.domain.models.Album;

import java.util.List;

public interface EnrichAlbumsUseCase {

    List<Album> enrichAndReturnAlbumsFromExternalAPI();
}
