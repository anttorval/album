package org.example.album.infrastructure.adapters.input.rest.controllers;

import org.example.album.application.services.AlbumService;
import org.example.album.application.services.dtos.AlbumDTO;
import org.example.album.application.services.dtos.PhotoDTO;
import org.example.album.infrastructure.adapters.input.rest.converters.AlbumDTOToGetAlbumsResponseConverter;
import org.example.album.infrastructure.adapters.input.rest.responses.GetAlbumsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlbumController.class)
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @MockBean
    private AlbumDTOToGetAlbumsResponseConverter albumDTOToGetAlbumsResponseConverter;

    private List<AlbumDTO> albumDTOList;

    @BeforeEach
    void init() {
        albumDTOList = this.getValidAlbumDTOList();
    }

    @Test
    void whenGetAllAlbumsFromDB_thenSucceed() throws Exception {

        // Given
        GetAlbumsResponse getAlbumsResponse = GetAlbumsResponse.builder()
                .albums(albumDTOList)
                .build();

        when(albumService.getAllAlbumsWithPhotos()).thenReturn(albumDTOList);
        when(albumDTOToGetAlbumsResponseConverter.convert(albumDTOList)).thenReturn(getAlbumsResponse);

        // Then
        mockMvc.perform(get("/v1/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.albums").exists())
                .andExpect(jsonPath("$.albums").isArray())
                .andExpect(jsonPath("$.albums").isNotEmpty())
                .andExpect(jsonPath("$.albums[0].id").value(albumDTOList.get(0).getId()))
                .andExpect(jsonPath("$.albums[0].title").value(albumDTOList.get(0).getTitle()))
                .andExpect(jsonPath("$.albums[0].photos").exists())
                .andExpect(jsonPath("$.albums[0].photos").isArray())
                .andExpect(jsonPath("$.albums[0].photos").isNotEmpty())
                .andExpect(jsonPath("$.albums[0].photos[0].id").value(albumDTOList.get(0).getPhotos().get(0).getId()))
                .andExpect(jsonPath("$.albums[0].photos[0].title").value(albumDTOList.get(0).getPhotos().get(0).getTitle()))
                .andExpect(jsonPath("$.albums[0].photos[0].url").value(albumDTOList.get(0).getPhotos().get(0).getUrl()))
                .andExpect(jsonPath("$.albums[0].photos[0].thumbnailUrl").value(albumDTOList.get(0).getPhotos().get(0).getThumbnailUrl()))
                .andDo(print());
    }

    @Test
    void whenEnrichAndSaveAlbumsWithPhotos_thenSucceed() throws Exception {
        // Then
        mockMvc.perform(post("/v1/albums/enrich"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void whenEnrichAndReturnAlbumsWithPhotos_thenSucceed() throws Exception {

        // Given
        GetAlbumsResponse getAlbumsResponse = GetAlbumsResponse.builder()
                .albums(albumDTOList)
                .build();

        when(albumService.enrichAndReturnAlbums()).thenReturn(albumDTOList);
        when(albumDTOToGetAlbumsResponseConverter.convert(albumDTOList)).thenReturn(getAlbumsResponse);

        // Then
        mockMvc.perform(get("/v1/albums/enrich"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.albums").exists())
                .andExpect(jsonPath("$.albums").isArray())
                .andExpect(jsonPath("$.albums").isNotEmpty())
                .andExpect(jsonPath("$.albums[0].id").value(albumDTOList.get(0).getId()))
                .andExpect(jsonPath("$.albums[0].title").value(albumDTOList.get(0).getTitle()))
                .andExpect(jsonPath("$.albums[0].photos").exists())
                .andExpect(jsonPath("$.albums[0].photos").isArray())
                .andExpect(jsonPath("$.albums[0].photos").isNotEmpty())
                .andExpect(jsonPath("$.albums[0].photos[0].id").value(albumDTOList.get(0).getPhotos().get(0).getId()))
                .andExpect(jsonPath("$.albums[0].photos[0].title").value(albumDTOList.get(0).getPhotos().get(0).getTitle()))
                .andExpect(jsonPath("$.albums[0].photos[0].url").value(albumDTOList.get(0).getPhotos().get(0).getUrl()))
                .andExpect(jsonPath("$.albums[0].photos[0].thumbnailUrl").value(albumDTOList.get(0).getPhotos().get(0).getThumbnailUrl()))
                .andDo(print());
    }

    private List<AlbumDTO> getValidAlbumDTOList() {
        List<AlbumDTO> albumDTOs = new ArrayList<>();
        PhotoDTO photoDTO = PhotoDTO.builder()
                .id(1L)
                .title("Title photo1")
                .url("www.url.com")
                .thumbnailUrl("www.thumbnailUrl.com")
                .build();

        List<PhotoDTO> photoDTOs = new ArrayList<>();
        photoDTOs.add(photoDTO);

        AlbumDTO albumDTO = AlbumDTO.builder()
                .id(1L)
                .title("Title album1")
                .photos(photoDTOs)
                .build();
        albumDTOs.add(albumDTO);

        return albumDTOs;
    }
}
