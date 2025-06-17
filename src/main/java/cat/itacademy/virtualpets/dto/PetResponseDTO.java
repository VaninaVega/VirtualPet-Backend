package cat.itacademy.virtualpets.dto;

import cat.itacademy.virtualpets.model.PetColorEnum;
import cat.itacademy.virtualpets.model.PetTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Schema(description = "Data Transfer Object for creating a new pet")
public class PetResponseDTO {
    private Long id;
    private String name;
    private PetTypeEnum type;
    private PetColorEnum color;
    private int energy;
    private boolean hungry;
    private int fun;
}

