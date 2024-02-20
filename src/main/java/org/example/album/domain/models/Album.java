package org.example.album.domain.models;

import java.util.List;
import java.util.Objects;

public class Album {

    private Long id;
    private String title;
    private List<Photo> photos;

    private Album(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setPhotos(builder.photos);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) && Objects.equals(title, album.title) && Objects.equals(photos, album.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, photos);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", photos=" + photos +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String title;
        private List<Photo> photos;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder photos(List<Photo> val) {
            photos = val;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }
}
