package artgallery.artwork_management_system.service;

import artgallery.artwork_management_system.dto.ArtworkDTO;

import java.util.List;

public interface ArtworkService {
    ArtworkDTO createArtwork(ArtworkDTO artworkDTO);
    List<ArtworkDTO> getAllArtworks();
    ArtworkDTO getArtworkById(Long id);
    ArtworkDTO updateArtwork(Long id, ArtworkDTO artworkDTO);
    void deleteArtwork(Long id);
    List<ArtworkDTO> searchArtworksByTitle(String title);
    List<ArtworkDTO> getArtworksByArtistId(Long artistId);
}