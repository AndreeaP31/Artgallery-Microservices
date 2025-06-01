package artgallery.artwork_management_system.repository;

import artgallery.artwork_management_system.domain.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    List<Artwork> findByTitleContainingIgnoreCase(String title);
    List<Artwork> findByArtistId(Long artistId);
    Optional<Artwork> findByTitle(String title);
}