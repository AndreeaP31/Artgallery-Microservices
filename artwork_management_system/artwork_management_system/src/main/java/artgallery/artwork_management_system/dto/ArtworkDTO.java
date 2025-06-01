package artgallery.artwork_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtworkDTO {

    private Long id;

    @NotBlank(message = "Artwork title cannot be blank")
    @Size(min = 2, max = 200, message = "Artwork title must be between 2 and 200 characters")
    private String title;

    @NotNull(message = "Artist ID cannot be null")
    private Long artistId;

    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate creationDate;

    @Size(max = 100, message = "Medium must be at most 100 characters")
    private String medium;

    @Size(max = 100, message = "Dimensions must be at most 100 characters")
    private String dimensions;

    private String description;

    private Double price;
}
