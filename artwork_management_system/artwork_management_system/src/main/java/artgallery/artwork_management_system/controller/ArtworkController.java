package artgallery.artwork_management_system.controller;

import artgallery.artwork_management_system.dto.ArtworkDTO;
import artgallery.artwork_management_system.service.ArtworkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;

    @Autowired
    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @PostMapping
    public ResponseEntity<ArtworkDTO> createArtwork(@Valid @RequestBody ArtworkDTO artworkDTO) {
        ArtworkDTO createdArtwork = artworkService.createArtwork(artworkDTO);
        return new ResponseEntity<>(createdArtwork, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArtworkDTO>> getAllArtworks() {
        List<ArtworkDTO> artworks = artworkService.getAllArtworks();
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDTO> getArtworkById(@PathVariable Long id) {
        ArtworkDTO artwork = artworkService.getArtworkById(id);
        return ResponseEntity.ok(artwork);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtworkDTO> updateArtwork(@PathVariable Long id, @Valid @RequestBody ArtworkDTO artworkDTO) {
        ArtworkDTO updatedArtwork = artworkService.updateArtwork(id, artworkDTO);
        return ResponseEntity.ok(updatedArtwork);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArtworkDTO>> searchArtworksByTitle(@RequestParam String title) {
        List<ArtworkDTO> artworks = artworkService.searchArtworksByTitle(title);
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<ArtworkDTO>> getArtworksByArtistId(@PathVariable Long artistId) {
        List<ArtworkDTO> artworks = artworkService.getArtworksByArtistId(artistId);
        return ResponseEntity.ok(artworks);
    }
}