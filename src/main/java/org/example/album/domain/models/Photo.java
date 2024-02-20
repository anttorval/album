package org.example.album.domain.models;

import java.util.Objects;

public class Photo {

    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    private Photo(Builder builder) {
        setId(builder.id);
        setAlbumId(builder.albumId);
        setTitle(builder.title);
        setUrl(builder.url);
        setThumbnailUrl(builder.thumbnailUrl);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(albumId, photo.albumId) && Objects.equals(title, photo.title) && Objects.equals(url, photo.url) && Objects.equals(thumbnailUrl, photo.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumId, title, url, thumbnailUrl);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private Long albumId;
        private String title;
        private String url;
        private String thumbnailUrl;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder albumId(Long val) {
            albumId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder thumbnailUrl(String val) {
            thumbnailUrl = val;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }
}
