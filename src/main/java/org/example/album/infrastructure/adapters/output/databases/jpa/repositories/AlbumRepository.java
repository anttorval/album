package org.example.album.infrastructure.adapters.output.databases.jpa.repositories;

import org.example.album.infrastructure.adapters.output.databases.jpa.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity,Long> {
}
