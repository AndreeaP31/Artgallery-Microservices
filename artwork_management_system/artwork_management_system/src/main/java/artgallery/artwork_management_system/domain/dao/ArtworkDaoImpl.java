package artgallery.artwork_management_system.domain.dao;

import artgallery.artwork_management_system.domain.Artwork;
import artgallery.artwork_management_system.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ArtworkDao interface.
 * Uses Spring Data JPA repository for database operations.
 */
@Repository
public class ArtworkDaoImpl implements ArtworkDao {

    private final ArtworkRepository artworkRepository;

    @Autowired
    public ArtworkDaoImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Override
    public Artwork save(Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    @Override
    public List<Artwork> findAll() {
        return artworkRepository.findAll();
    }

    @Override
    public Optional<Artwork> findById(Long id) {
        return artworkRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return artworkRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        artworkRepository.deleteById(id);
    }

    @Override
    public List<Artwork> findByTitleContainingIgnoreCase(String title) {
        return artworkRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Artwork> findByArtistId(Long artistId) {
        return artworkRepository.findByArtistId(artistId);
    }

    @Override
    public Optional<Artwork> findByTitle(String title) {
        return artworkRepository.findByTitle(title);
    }
}
