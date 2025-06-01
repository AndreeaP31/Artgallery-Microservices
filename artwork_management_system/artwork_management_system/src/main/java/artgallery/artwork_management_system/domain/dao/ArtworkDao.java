package artgallery.artwork_management_system.domain.dao;

import artgallery.artwork_management_system.domain.Artwork;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Artwork entity.
 * Provides methods for CRUD operations and custom queries.
 */
public interface ArtworkDao {

    /**
     * Save an artwork to the database.
     *
     * @param artwork the artwork to save
     * @return the saved artwork with generated ID
     */
    Artwork save(Artwork artwork);

    /**
     * Find all artworks in the database.
     *
     * @return a list of all artworks
     */
    List<Artwork> findAll();

    /**
     * Find an artwork by ID.
     *
     * @param id the ID of the artwork to find
     * @return an Optional containing the artwork if found, or empty if not found
     */
    Optional<Artwork> findById(Long id);

    /**
     * Check if an artwork with the given ID exists.
     *
     * @param id the ID to check
     * @return true if an artwork with the ID exists, false otherwise
     */
    boolean existsById(Long id);

    /**
     * Delete an artwork by ID.
     *
     * @param id the ID of the artwork to delete
     */
    void deleteById(Long id);

    /**
     * Find artworks whose titles contain the given string (case-insensitive).
     *
     * @param title the title to search for
     * @return a list of artworks whose titles contain the given string
     */
    List<Artwork> findByTitleContainingIgnoreCase(String title);

    /**
     * Find artworks by artist ID.
     *
     * @param artistId the artist ID to search for
     * @return a list of artworks by the given artist
     */
    List<Artwork> findByArtistId(Long artistId);

    /**
     * Find an artwork by exact title.
     *
     * @param title the title to search for
     * @return an Optional containing the artwork if found, or empty if not found
     */
    Optional<Artwork> findByTitle(String title);
}