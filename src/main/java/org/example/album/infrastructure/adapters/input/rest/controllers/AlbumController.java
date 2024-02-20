package org.example.album.infrastructure.adapters.input.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.album.application.services.AlbumService;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.infrastructure.adapters.input.rest.converters.AlbumDTOToGetAlbumsResponseConverter;
import org.example.album.infrastructure.adapters.input.rest.responses.GetAlbumsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/albums")
@RequiredArgsConstructor
@Validated
@Tag(name = "v1/albums")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Response OK"),
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "400", description = "The request is not well formed"),
        @ApiResponse(responseCode = "401", description = "Not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumDTOToGetAlbumsResponseConverter albumDTOToGetAlbumsResponseConverter;

    @GetMapping
    @Operation(
            method = "GET",
            description = "Returns all albums with their photos from database"
    )
    public ResponseEntity<GetAlbumsResponse> getAllAlbumsFromDB()  {

        List<AlbumDTO> albumDTOList = this.albumService.getAllAlbumsWithPhotos();

        GetAlbumsResponse getAlbumsResponse = this.albumDTOToGetAlbumsResponseConverter.convert(albumDTOList);

        return ResponseEntity.ok(getAlbumsResponse);
    }

    @PostMapping("/enrich")
    @Operation(
            method = "POST",
            description = "Enrich albums with their photos and save in the database"
    )
    public ResponseEntity<Void> enrichAndSaveAlbumsWithPhotos() {

        this.albumService.enrichAndSaveAlbums();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/enrich")
    @Operation(
            method = "GET",
            description = "Enrich albums with their photos and return without save in the database"
    )
    public ResponseEntity<GetAlbumsResponse> enrichAndReturnAlbumsWithPhotos() {

        List<AlbumDTO> albumDTOList = this.albumService.enrichAndReturnAlbums();

        GetAlbumsResponse getAlbumsResponse = this.albumDTOToGetAlbumsResponseConverter.convert(albumDTOList);

        return ResponseEntity.ok(getAlbumsResponse);
    }

}
