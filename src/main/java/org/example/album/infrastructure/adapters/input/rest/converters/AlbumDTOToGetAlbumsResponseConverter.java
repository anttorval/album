package org.example.album.infrastructure.adapters.input.rest.converters;

import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.infrastructure.adapters.input.rest.responses.GetAlbumsResponse;

import java.util.List;

public interface AlbumDTOToGetAlbumsResponseConverter {

    GetAlbumsResponse convert (List<AlbumDTO> albumDTOs);
}
