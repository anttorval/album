package org.example.album.application.services.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDTO {

    private Long id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
