package org.example.album.application.adapters;

import lombok.RequiredArgsConstructor;
import org.example.album.application.exceptions.ValidationException;
import org.example.album.application.ports.dtos.AlbumDTO;
import org.example.album.application.ports.dtos.PhotoDTO;
import org.example.album.application.ports.AlbumProviderPort;
import org.example.album.application.ports.PhotoProviderPort;
import org.example.album.domain.models.Album;
import org.example.album.domain.models.Photo;
import org.example.album.domain.ports.input.EnrichAlbumsUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EnrichAlbumsAdapter implements EnrichAlbumsUseCase {

    private final AlbumProviderPort albumProviderPort;
    private final PhotoProviderPort photoProviderPort;

    @Override
    public List<Album> enrichAndReturnAlbumsFromExternalAPI() {
        Map<String, Object> dataProvider = this.getAlbumsAndPhotosFromProvider();

        if(Objects.nonNull(dataProvider)){
            Map<Long, Album> albumMap = this.enrichAndReturnAlbumWithPhotos(dataProvider);
            return new ArrayList<>(albumMap.values());
        }else{
            throw new ValidationException("The response of providers is incorrect.");
        }
    }

    private Map<String, Object> getAlbumsAndPhotosFromProvider() {
        Mono<List<AlbumDTO>> albumDTOListMono = this.albumProviderPort.getAllAlbums();
        Mono<List<PhotoDTO>> photoDTOListMono = this.photoProviderPort.getAllPhotos();

        return Mono.zip(albumDTOListMono, photoDTOListMono, (albums, photos) -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("albums", albums);
                    map.put("photos", photos);
                    return map;
                })
                .block();
    }

    private Map<Long, Album> enrichAndReturnAlbumWithPhotos(Map<String, Object> dataProvider) {
        List<AlbumDTO> albumDTOList = (List<AlbumDTO>) dataProvider.get("albums");
        List<PhotoDTO> photoDTOList = (List<PhotoDTO>) dataProvider.get("photos");

        Map<Long, Album> albumMap = new HashMap<>();

        albumDTOList.forEach(albumDTO -> {
            Album album = Album.builder()
                    .id(albumDTO.getId())
                    .title(albumDTO.getTitle())
                    .photos(new ArrayList<>())
                    .build();

            albumMap.put(album.getId(), album);
        });

        photoDTOList.forEach(photoDTO -> {
            Long albumId = photoDTO.getAlbumId();
            Album album = albumMap.get(albumId);
            if (album != null) {
                Photo photo = Photo.builder()
                        .id(photoDTO.getId())
                        .title(photoDTO.getTitle())
                        .url(photoDTO.getUrl())
                        .thumbnailUrl(photoDTO.getThumbnailUrl())
                        .albumId(photoDTO.getAlbumId())
                        .build();

                album.getPhotos().add(photo);
            }
        });

        return albumMap;
    }
}
