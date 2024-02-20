package org.example.album.infrastructure.adapters.input.rest.converters.impl;

import org.example.album.application.exceptions.ValidationException;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.infrastructure.adapters.input.rest.converters.AlbumDTOToGetAlbumsResponseConverter;
import org.example.album.infrastructure.adapters.input.rest.responses.GetAlbumsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AlbumDTOToGetAlbumsResponseImpl implements AlbumDTOToGetAlbumsResponseConverter {

    @Override
    public GetAlbumsResponse convert(List<AlbumDTO> albumDTOs) {

        if(Objects.isNull(albumDTOs)){
            throw new ValidationException("albumDTOs cannot be null");
        }

        return GetAlbumsResponse.builder()
                .albums(albumDTOs)
                .build();
    }
}
