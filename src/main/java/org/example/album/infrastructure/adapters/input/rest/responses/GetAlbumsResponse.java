package org.example.album.infrastructure.adapters.input.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.album.application.services.dtos.AlbumDTO;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAlbumsResponse {

    private List<AlbumDTO> albums;
}
