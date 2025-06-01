package artgallery.artwork_management_system.service;

import org.modelmapper.ModelMapper;
import artgallery.artwork_management_system.domain.Artwork;
import artgallery.artwork_management_system.dto.ArtworkDTO;
import artgallery.artwork_management_system.repository.ArtworkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtworkServiceImpl(ArtworkRepository artworkRepository, ModelMapper modelMapper) {
        this.artworkRepository = artworkRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ArtworkDTO createArtwork(ArtworkDTO artworkDTO) {
        // Check if artwork with same title already exists
        if (artworkRepository.findByTitle(artworkDTO.getTitle()).isPresent()) {
            throw new IllegalArgumentException("Artwork with title '" + artworkDTO.getTitle() + "' already exists");
        }

        Artwork artwork = modelMapper.map(artworkDTO, Artwork.class);
        Artwork savedArtwork = artworkRepository.save(artwork);
        return modelMapper.map(savedArtwork, ArtworkDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArtworkDTO> getAllArtworks() {
        return artworkRepository.findAll().stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ArtworkDTO getArtworkById(Long id) {
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artwork not found with id: " + id));
        return modelMapper.map(artwork, ArtworkDTO.class);
    }

    @Override
    @Transactional
    public ArtworkDTO updateArtwork(Long id, ArtworkDTO artworkDTO) {
        Artwork existingArtwork = artworkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artwork not found with id: " + id));

        // Check if another artwork with the same title exists (excluding current one)
        artworkRepository.findByTitle(artworkDTO.getTitle())
                .filter(artwork -> !artwork.getId().equals(id))
                .ifPresent(artwork -> {
                    throw new IllegalArgumentException("Another artwork with title '" + artworkDTO.getTitle() + "' already exists");
                });

        // Update fields using ModelMapper or manually
        modelMapper.map(artworkDTO, existingArtwork);
        existingArtwork.setId(id); // Ensure ID is preserved

        Artwork updatedArtwork = artworkRepository.save(existingArtwork);
        return modelMapper.map(updatedArtwork, ArtworkDTO.class);
    }

    @Override
    @Transactional
    public void deleteArtwork(Long id) {
        if (!artworkRepository.existsById(id)) {
            throw new EntityNotFoundException("Artwork not found with id: " + id + ". Cannot delete.");
        }
        artworkRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArtworkDTO> searchArtworksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Search title cannot be null or empty");
        }

        return artworkRepository.findByTitleContainingIgnoreCase(title.trim()).stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArtworkDTO> getArtworksByArtistId(Long artistId) {
        if (artistId == null || artistId <= 0) {
            throw new IllegalArgumentException("Artist ID must be a positive number");
        }

        return artworkRepository.findByArtistId(artistId).stream()
                .map(artwork -> modelMapper.map(artwork, ArtworkDTO.class))
                .collect(Collectors.toList());
    }
}